package at.fikar.raphael.cashhelper.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import at.fikar.raphael.cashhelper.dto.Account;
import at.fikar.raphael.cashhelper.dto.AccountStore;
import at.fikar.raphael.cashhelper.dto.LogStore;
import at.fikar.raphael.cashhelper.util.XMLParser;

public class FileSaver {

	private final AccountStore accountStore;
	private final LogStore logStore;
	private File saveFile;

	public FileSaver() {
		this.accountStore = AccountStore.getInstance();
		this.logStore = LogStore.getInstance();
	}

	public void saveAccounts(final File saveFile) throws IOException {
		this.saveFile = saveFile;
		final String writeThis = getXMLForAccounts();
		writeToSaveFile(writeThis);
	}

	private String getXMLForAccounts() {
		final List<Account> allAccounts = accountStore.getAllAccounts();
		final StringBuilder allAccountsXML = new StringBuilder();
		for (final Account account : allAccounts) {
			allAccountsXML.append(XMLParser.convertAccountToXML(account));
		}
		return allAccountsXML.toString();
	}

	private void writeToSaveFile(final String contentToWrite) throws IOException {
		final BufferedWriter writer = new BufferedWriter(new FileWriter(saveFile));
		writer.write(contentToWrite);
		writer.close();
	}

}
