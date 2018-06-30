package at.fikar.raphael.cashhelper.util;

import static at.fikar.raphael.cashhelper.util.XMLTags.ACCOUNT_CLOSE;
import static at.fikar.raphael.cashhelper.util.XMLTags.ACCOUNT_OPEN;
import static at.fikar.raphael.cashhelper.util.XMLTags.CREATION_CLOSE;
import static at.fikar.raphael.cashhelper.util.XMLTags.CREATION_OPEN;
import static at.fikar.raphael.cashhelper.util.XMLTags.LASTEDIT_CLOSE;
import static at.fikar.raphael.cashhelper.util.XMLTags.LASTEDIT_OPEN;
import static at.fikar.raphael.cashhelper.util.XMLTags.LINE_SEPERATOR;
import static at.fikar.raphael.cashhelper.util.XMLTags.NAME_CLOSE;
import static at.fikar.raphael.cashhelper.util.XMLTags.NAME_OPEN;
import static at.fikar.raphael.cashhelper.util.XMLTags.VALUE_CLOSE;
import static at.fikar.raphael.cashhelper.util.XMLTags.VALUE_OPEN;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;

import at.fikar.raphael.cashhelper.dto.Account;

public class XMLParserTest {

	private Account testAccount;

	@Test
	public void convertGoodAccountToXML() {
		final String name = "testAccount";
		final double value = 693.78;
		final Date lastEdit = new Date(2L);
		final Date creation = new Date(1L);
		testAccount = new Account(name, value, lastEdit, creation);

		final String outCome = XMLParser.convertAccountToXML(testAccount);

		checkTags(outCome);
		checkOutcomeInOrder(outCome);

	}

	private void checkTags(final String toCheck) {
		assertTrue(toCheck.startsWith(ACCOUNT_OPEN));
		assertTrue(toCheck.contains(NAME_OPEN));
		assertTrue(toCheck.contains(NAME_CLOSE));
		assertTrue(toCheck.contains(VALUE_OPEN));
		assertTrue(toCheck.contains(VALUE_CLOSE));
		assertTrue(toCheck.contains(LASTEDIT_OPEN));
		assertTrue(toCheck.contains(LASTEDIT_CLOSE));
		assertTrue(toCheck.contains(CREATION_OPEN));
		assertTrue(toCheck.contains(CREATION_CLOSE));
		assertTrue(toCheck.endsWith(ACCOUNT_CLOSE));
	}

	private void checkOutcomeInOrder(final String input) {
		String workstring = input;

		assertTrue(workstring.startsWith(ACCOUNT_OPEN));
		workstring = remove(ACCOUNT_OPEN, workstring);

		assertTrue(workstring.startsWith(LINE_SEPERATOR));
		workstring = remove(LINE_SEPERATOR, workstring);

		assertTrue(workstring.startsWith(NAME_OPEN));
		workstring = remove(NAME_OPEN, workstring);

		assertTrue(workstring.startsWith(testAccount.getName()));
		workstring = remove(testAccount.getName(), workstring);

		assertTrue(workstring.startsWith(NAME_CLOSE));
		workstring = remove(NAME_CLOSE, workstring);

		assertTrue(workstring.startsWith(LINE_SEPERATOR));
		workstring = remove(LINE_SEPERATOR, workstring);

		assertTrue(workstring.startsWith(VALUE_OPEN));
		workstring = remove(VALUE_OPEN, workstring);

		assertTrue(workstring.startsWith("" + testAccount.getValue()));
		workstring = remove("" + testAccount.getValue(), workstring);

		assertTrue(workstring.startsWith(VALUE_CLOSE));
		workstring = remove(VALUE_CLOSE, workstring);

		assertTrue(workstring.startsWith(LINE_SEPERATOR));
		workstring = remove(LINE_SEPERATOR, workstring);

		assertTrue(workstring.startsWith(LASTEDIT_OPEN));
		workstring = remove(LASTEDIT_OPEN, workstring);

		assertTrue(workstring.startsWith("" + testAccount.getLastEditAsLong()));
		workstring = remove("" + testAccount.getLastEditAsLong(), workstring);

		assertTrue(workstring.startsWith(LASTEDIT_CLOSE));
		workstring = remove(LASTEDIT_CLOSE, workstring);

		assertTrue(workstring.startsWith(LINE_SEPERATOR));
		workstring = remove(LINE_SEPERATOR, workstring);

		assertTrue(workstring.startsWith(CREATION_OPEN));
		workstring = remove(CREATION_OPEN, workstring);

		assertTrue(workstring.startsWith("" + testAccount.getDateCreationAsLong()));
		workstring = remove("" + testAccount.getDateCreationAsLong(), workstring);

		assertTrue(workstring.startsWith(CREATION_CLOSE));
		workstring = remove(CREATION_CLOSE, workstring);

		assertTrue(workstring.startsWith(LINE_SEPERATOR));
		workstring = remove(LINE_SEPERATOR, workstring);

		assertTrue(workstring.startsWith(ACCOUNT_CLOSE));
		workstring = remove(ACCOUNT_CLOSE, workstring);

		assertTrue(workstring.isEmpty());
	}

	private String remove(final String toRemove, final String from) {
		return from.replaceFirst(toRemove, "");
	}

}
