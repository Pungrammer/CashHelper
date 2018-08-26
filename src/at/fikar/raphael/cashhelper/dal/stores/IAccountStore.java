package at.fikar.raphael.cashhelper.dal.stores;

import at.fikar.raphael.cashhelper.dal.Persistable;
import at.fikar.raphael.cashhelper.dal.dto.Account;
import at.fikar.raphael.cashhelper.dal.dto.Id;

import java.io.IOException;
import java.util.Set;

public interface IAccountStore extends Persistable {

    /**
     * Saves a new {@link Account} and returns the new {@link Id}.
     *
     * @param account The {@link Account} to save
     * @return The new {@link Id} of the {@link Account}
     */
    Id<Account> put(Account account);

    /**
     * Returns the {@link Account} with the {@link Id} or null if the {@link Account} does not exist.
     *
     * @param accountId The {@link Id} of the {@link Account} to lookup
     * @return The {@link Account} or null if the {@link Account} does not exist.
     */
    Account get(Id<Account> accountId);

    /**
     * Returns all saved {@link Account}s.
     *
     * @return All saved {@link Account}s
     */
    Set<Account> getAll();

    /**
     * Deletes the account with the given ID
     *
     * @param accountId Id to delete
     */
    void delete(Id<Account> accountId);
}
