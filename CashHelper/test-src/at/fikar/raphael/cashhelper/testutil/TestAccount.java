package at.fikar.raphael.cashhelper.testutil;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import at.fikar.raphael.cashhelper.dto.Account;
import at.fikar.raphael.cashhelper.util.TimeHandler;

public class TestAccount {

	private static final int randomUpperLimit = 9999;

	private static final List<Account> testAccounts = new ArrayList<Account>();

	private TestAccount() {

	}

	public static List<Account> getTestAccounts(final int howMany) throws ParseException {
		for (int i = 0; i < howMany; i++) {
			testAccounts.add(buildRandomAccount());
		}
		return testAccounts;
	}

	private static Account buildRandomAccount() throws ParseException {
		final Random r = new Random();
		final String name = "testAccount" + r.nextInt(randomUpperLimit);
		final double value = r.nextDouble();
		final String creationDate = TimeHandler.nowAsString();
		final String lastEdit = TimeHandler.nowAsString();

		return new Account(name, value, creationDate, lastEdit);
	}

}
