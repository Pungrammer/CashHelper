package at.fikar.raphael.cashhelper.dal.stores;

import at.fikar.raphael.cashhelper.dal.dto.Account;
import at.fikar.raphael.cashhelper.dal.dto.Id;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class AccountStore implements IAccountStore {

	private final Map<String, Account> allAccounts;

	private AccountStore() {
		allAccounts = new ConcurrentHashMap<>();
	}

    @Override
    public Id<Account> put(Account account) {

        return null;
    }

    @Override
    public Account get(Id<Account> accountId) {
        return null;
    }

    @Override
    public Set<Account> getAll() {
        return null;
    }

    private void persist(final Account account){

    }
}
