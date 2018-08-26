package at.fikar.raphael.cashhelper.dal.stores;

import at.fikar.raphael.cashhelper.dal.Persistable;
import at.fikar.raphael.cashhelper.dal.dto.Account;
import at.fikar.raphael.cashhelper.dal.dto.Entry;
import at.fikar.raphael.cashhelper.dal.dto.Id;

import java.util.Collection;

public interface IEntryStore extends Persistable {

    Id<Entry> put(Entry entry);

    Entry get(Id<Entry> entryId);

    Collection<Entry> getAll();

    Collection<Entry> getAllForAccount(Id<Account> accountId);

    void delete(Id<Entry> entryId);

}
