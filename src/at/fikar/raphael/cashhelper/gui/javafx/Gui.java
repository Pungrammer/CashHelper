package at.fikar.raphael.cashhelper.gui.javafx;

import at.fikar.raphael.cashhelper.dal.PersistenceWorker;
import at.fikar.raphael.cashhelper.gui.javafx.actionnotification.UpdateNotifier;
import at.fikar.raphael.cashhelper.gui.javafx.scenes.OverViewScene;
import at.fikar.raphael.cashhelper.injection.DALModule;
import at.fikar.raphael.cashhelper.injection.FileModule;
import at.fikar.raphael.cashhelper.injection.MiscModule;
import at.fikar.raphael.cashhelper.injection.TableModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.stage.Stage;

public class Gui extends Application {

    private static Thread persistenceThread;
    private static Thread updateNotificationThread;

    private static Injector injector;


    public static void main(final String[] args) {
        injector = bindModules();

        persistenceThread = new Thread(injector.getInstance(PersistenceWorker.class));
        persistenceThread.start();

        updateNotificationThread = new Thread(injector.getInstance(UpdateNotifier.class));
        updateNotificationThread.start();

        launch(args);
    }

    private static Injector bindModules() {
        return Guice.createInjector(
                new MiscModule(),
                new FileModule(),
                new DALModule(),
                new TableModule()
        );
    }

    @Override
    public void start(Stage primaryStage) {
        OverViewScene overviewScene = injector.getInstance(OverViewScene.class);

        primaryStage.setTitle("Cash helper v0.1");
        primaryStage.setScene(overviewScene.getScene());
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        persistenceThread.interrupt();
        persistenceThread.join();
        injector.getInstance(PersistenceWorker.class).persistNow();

        updateNotificationThread.interrupt();
        updateNotificationThread.join();
    }


}
