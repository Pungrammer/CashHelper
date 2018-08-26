package at.fikar.raphael.cashhelper.file;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import at.fikar.raphael.cashhelper.dal.dto.Account;
import at.fikar.raphael.cashhelper.testutil.TestAccount;

public class FileLoaderTest {

	List<Account> testAccount = new ArrayList<Account>();

	@Before
	public void setup() throws IOException, ParseException {
		final File testFile = new File("testFile.xml");
		testFile.createNewFile();
		testAccount = TestAccount.getTestAccounts(5);
		// WIP
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
