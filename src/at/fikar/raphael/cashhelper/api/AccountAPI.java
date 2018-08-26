package at.fikar.raphael.cashhelper.api;

import at.fikar.raphael.cashhelper.dal.dto.Account;
import at.fikar.raphael.cashhelper.dal.dto.Id;
import at.fikar.raphael.cashhelper.dal.stores.IAccountStore;
import com.google.inject.Inject;

import java.time.Clock;
import java.util.Date;
import java.util.Set;

public class AccountAPI {

    private final IAccountStore accountStore;
    private final Clock clock;

    @Inject
    public AccountAPI(final IAccountStore accountStore, final Clock clock){
        this.accountStore = accountStore;
        this.clock = clock;
    }

    public Id<Account> createNewAccount(final String accountName){
        Account account = new Account(null, accountName, 0.0, new Date(clock.millis()), new Date(clock.millis()));
        return accountStore.put(account);
    }

    public void deleteAccount(final Id<Account> accountId){
        accountStore.delete(accountId);
    }

    public Set<Account> getAllAccounts(){
        return accountStore.getAll();
    }

}
