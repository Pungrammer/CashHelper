package at.fikar.raphael.cashhelper.dal.stores;

import at.fikar.raphael.cashhelper.dal.dto.Account;
import at.fikar.raphael.cashhelper.dal.dto.Entry;
import at.fikar.raphael.cashhelper.dal.dto.Id;
import at.fikar.raphael.cashhelper.file.IFileSource;
import at.fikar.raphael.cashhelper.gui.javafx.actionnotification.UpdateNotificationType;
import at.fikar.raphael.cashhelper.gui.javafx.actionnotification.UpdateNotifier;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Singleton
public class EntryStore implements IEntryStore {

    private final Map<Id<Entry>, Entry> cache;
    private final UpdateNotifier updateNotifier;
    private final IFileSource fileSource;

    private long entryIdSequence;

    @Inject
    public EntryStore(final IFileSource fileSource,
                      final HashMap<Id<Entry>, Entry> cache,
                      final UpdateNotifier updateNotifier)
            throws IOException
    {
        this.fileSource = fileSource;
        this.cache = cache;
        this.updateNotifier = updateNotifier;

        Collection<Entry> entries = fileSource.loadEntries();
        if (!entries.isEmpty()) {
            entries.forEach(account -> {
                if (account.getId().getRaw() > entryIdSequence) {
                    entryIdSequence = account.getId().getRaw() + 1;
                } else if (account.getId().getRaw() == entryIdSequence) {
                    entryIdSequence++;
                }
                this.put(account);
            });
        }
    }

    @Override
    public Id<Entry> put(Entry entry) {
        if (entry.getId() == null) {
            entry.setId(new Id<>(entryIdSequence++));
        }
        cache.put(entry.getId(), entry);
        updateNotifier.notifyAboutUpdate(UpdateNotificationType.ENTRY);

        return entry.getId();
    }

    @Override
    public Entry get(Id<Entry> entryId) {
        return cache.get(entryId);
    }

    @Override
    public Collection<Entry> getAll() {
        return cache.values();
    }

    @Override
    public Collection<Entry> getAllForAccount(Id<Account> accountId) {
        return cache.values().stream()
                .filter(entry -> entry.getAccountId().equals(accountId))
                .collect(Collectors.toSet());
    }

    @Override
    public void delete(Id<Entry> entryId) {
        cache.remove(entryId);
        updateNotifier.notifyAboutUpdate(UpdateNotificationType.ENTRY);
    }

    @Override
    public void persist() throws Exception {
        fileSource.saveEntries(cache.values());
    }
}
