package at.fikar.raphael.cashhelper.dal.stores;

import at.fikar.raphael.cashhelper.dal.dto.Account;
import at.fikar.raphael.cashhelper.dal.dto.Id;
import at.fikar.raphael.cashhelper.file.IFileSource;
import at.fikar.raphael.cashhelper.gui.javafx.actionnotification.UpdateNotificationType;
import at.fikar.raphael.cashhelper.gui.javafx.actionnotification.UpdateNotifier;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.io.IOException;
import java.util.*;

@Singleton
public class AccountStore implements IAccountStore {

    private final IFileSource source;
    private final Map<Id<Account>, Account> cache;
    private final UpdateNotifier updateNotifier;

    private long accountIdSequence = 1;

    @Inject
    public AccountStore(final IFileSource source,
                        final HashMap<Id<Account>, Account> cache,
                        final UpdateNotifier updateNotifier)
            throws IOException
    {
        this.source = source;
        this.cache = cache;
        this.updateNotifier = updateNotifier;

        Collection<Account> accounts = source.loadAccounts();
        if (!accounts.isEmpty()) {
            accounts.forEach(account -> {
                if (account.getId().getRaw() > accountIdSequence) {
                    accountIdSequence = account.getId().getRaw() + 1;
                } else if (account.getId().getRaw() == accountIdSequence) {
                    accountIdSequence++;
                }
                this.put(account);
            });
        }
    }

    @Override
    public Id<Account> put(Account account) {
        if (account.getId() == null) {
            account.setId(new Id<>(accountIdSequence++));
        }
        cache.put(account.getId(), account);
        updateNotifier.notifyAboutUpdate(UpdateNotificationType.ACCOUNT);
        return account.getId();
    }

    @Override
    public Account get(Id<Account> accountId) {
        return cache.get(accountId);
    }

    @Override
    public Set<Account> getAll() {
        return new HashSet<>(cache.values());
    }

    @Override
    public void delete(Id<Account> accountId) {
        cache.remove(accountId);
        updateNotifier.notifyAboutUpdate(UpdateNotificationType.ACCOUNT);
    }

    @Override
    public void persist() throws IOException {
        source.saveAccounts(cache.values());
    }
}
