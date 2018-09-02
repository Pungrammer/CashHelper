package at.fikar.raphael.cashhelper.gui.javafx.actionnotification;

import at.fikar.raphael.cashhelper.injection.provider.QueueProvider;
import at.fikar.raphael.cashhelper.logging.LogTypes;
import at.fikar.raphael.cashhelper.logging.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
public class UpdateNotifier implements Runnable {

    private final BlockingQueue<UpdateNotificationType> updateNotificationQueue;
    private final Map<UpdateNotificationType, List<IUpdateable>> notificationTargetMap;

    @Inject
    public UpdateNotifier(final QueueProvider queueProvider) {
        this.updateNotificationQueue = queueProvider.get();
        notificationTargetMap = new ConcurrentHashMap<>();

        //Populate map with default lists
        Arrays.stream(UpdateNotificationType.values())
                .forEach(updateNotificationType -> notificationTargetMap.put(updateNotificationType,
                        new ArrayList<>()));
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted() && Thread.currentThread().isAlive()) {
            try {
                UpdateNotificationType notificationType = updateNotificationQueue.take();
                List<IUpdateable> updateables = notificationTargetMap.get(notificationType);
                updateables.forEach(updateable -> {
                    try {
                        updateable.updateContent();
                    } catch (Exception e) {
                        Logger.log(LogTypes.ERROR, "Unable to update for " + notificationType.name());
                    }
                });
            } catch (InterruptedException e) {
                Logger.log(LogTypes.ERROR, "Got interrupted while waiting for notifications");
            }
        }
    }

    public void subscribeListener(final IUpdateable updateable) {
        List<IUpdateable> updateables = notificationTargetMap.get(updateable.getUpdateType());
        updateables.add(updateable);
        notificationTargetMap.put(updateable.getUpdateType(), updateables);
    }

    public void notifyAboutUpdate(final UpdateNotificationType type) {
        updateNotificationQueue.add(type);
    }

}
