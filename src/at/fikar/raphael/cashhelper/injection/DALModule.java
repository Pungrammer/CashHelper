package at.fikar.raphael.cashhelper.injection;

import at.fikar.raphael.cashhelper.dal.stores.AccountStore;
import at.fikar.raphael.cashhelper.dal.stores.IAccountStore;
import com.google.inject.AbstractModule;

public class DALModule extends AbstractModule {

    @Override
    protected void configure() {
        super.configure();
        bind(IAccountStore.class).to(AccountStore.class);
    }
}
