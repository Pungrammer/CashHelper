package at.fikar.raphael.cashhelper.dal.stores;

import at.fikar.raphael.cashhelper.dal.dto.Account;
import at.fikar.raphael.cashhelper.dal.dto.Id;

import java.util.Set;

public interface IAccountStore {

    /**
     * Saves a new {@link Account} and returns the new {@link Id}.
     *
     * @param account The {@link Account} to save
     * @return The new {@link Id} of the {@link Account}
     */
    Id<Account> put(final Account account);

    /**
     * Returns the {@link Account} with the {@link Id} or null if the {@link Account} does not exist.
     *
     * @param accountId The {@link Id} of the {@link Account} to lookup
     * @return The {@link Account} or null if the {@link Account} does not exist.
     */
    Account get(final Id<Account> accountId);

    /**
     * Returns all saved {@link Account}s.
     * @return All saved {@link Account}s
     */
    Set<Account> getAll();
}
