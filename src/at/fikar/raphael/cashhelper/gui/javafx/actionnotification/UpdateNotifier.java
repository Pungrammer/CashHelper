package at.fikar.raphael.cashhelper.gui.javafx.actionnotification;

import at.fikar.raphael.cashhelper.injection.provider.QueueProvider;
import at.fikar.raphael.cashhelper.logging.LogTypes;
import at.fikar.raphael.cashhelper.logging.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
public class UpdateNotifier implements Runnable {

    private final BlockingQueue<UpdateNotificationType> updateNotificationQueue;
    private final Map<UpdateNotificationType, List<Callable<Void>>> notificationTargetMap;

    @Inject
    public UpdateNotifier(final QueueProvider queueProvider) {
        this.updateNotificationQueue = queueProvider.get();
        notificationTargetMap = new ConcurrentHashMap<>();
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted() && Thread.currentThread().isAlive()) {
            try {
                UpdateNotificationType notificationType = updateNotificationQueue.take();
                List<Callable<Void>> callables = notificationTargetMap.get(notificationType);
                callables.forEach(callable -> {
                    try {
                        callable.call();
                    } catch (Exception e) {
                        Logger.log(LogTypes.ERROR, "Unable to update for " + notificationType.name());
                    }
                });
            } catch (InterruptedException e) {
                Logger.log(LogTypes.ERROR, "Got interrupted while waiting for notifications");
            }
        }
    }

    public void subscribeListener(final UpdateNotificationType type, final Callable<Void> callbackMethod) {
        List<Callable<Void>> callables = notificationTargetMap.get(type);
        callables.add(callbackMethod);
        notificationTargetMap.put(type, callables);

    }

    public void notifyAboutUpdate(final UpdateNotificationType type) {
        updateNotificationQueue.add(type);
    }

}
