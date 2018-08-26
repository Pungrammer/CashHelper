package at.fikar.raphael.cashhelper.injection;

import at.fikar.raphael.cashhelper.dal.stores.AccountStore;
import at.fikar.raphael.cashhelper.dal.stores.EntryStore;
import at.fikar.raphael.cashhelper.dal.stores.IAccountStore;
import at.fikar.raphael.cashhelper.dal.stores.IEntryStore;
import com.google.inject.AbstractModule;

public class DALModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IAccountStore.class).to(AccountStore.class);
        bind(IEntryStore.class).to(EntryStore.class);
    }
}
