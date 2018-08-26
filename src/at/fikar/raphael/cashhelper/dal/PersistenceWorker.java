package at.fikar.raphael.cashhelper.dal;

import at.fikar.raphael.cashhelper.dal.stores.IAccountStore;
import at.fikar.raphael.cashhelper.dal.stores.IEntryStore;
import at.fikar.raphael.cashhelper.logging.LogTypes;
import at.fikar.raphael.cashhelper.logging.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PersistenceWorker implements Runnable {

    private final IAccountStore accountStore;
    private final IEntryStore entryStore;

    @Inject
    public PersistenceWorker(final IAccountStore accountStore, final IEntryStore entryStore) {
        this.accountStore = accountStore;
        this.entryStore = entryStore;
    }

    @Override
    public void run() {
        while (Thread.currentThread().isAlive() && !Thread.currentThread().isInterrupted()) {
            try {
                persistNow();
            } catch (Exception e) {
                Logger.log(LogTypes.ERROR, "Unable to persist accounts:\n" + e.getMessage());
                e.getStackTrace();
            }

            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                Logger.log(LogTypes.ERROR, "Got interrupted while waiting:\n" + e.getMessage());
                return;
            }
        }
    }

    public void persistNow() throws Exception {
        accountStore.persist();
        entryStore.persist();
    }
}
