package at.fikar.raphael.cashhelper.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountStore {

	private static AccountStore instance = new AccountStore();

	private final Map<String, Account> allAccounts;

	private AccountStore() {
		allAccounts = new HashMap<String, Account>();
	}

	public static AccountStore getInstance() {
		return instance;
	}

	public void putAccount(final Account account) {
		allAccounts.put(account.getName(), account);
	}

	public void putAccounts(final Collection<Account> accounts) {
		for (final Account account : accounts) {
			putAccount(account);
		}
	}

	public List<Account> getAllAccounts() {
		final Collection<Account> accounts = allAccounts.values();
		final List<Account> outputList = new ArrayList<Account>();
		for (final Account currentAccount : accounts) {
			outputList.add(currentAccount);
		}
		return outputList;
	}

	public Account getAccountByName(final String name) {
		return allAccounts.get(name);
	}
}
