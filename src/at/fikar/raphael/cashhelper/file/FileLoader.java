package at.fikar.raphael.cashhelper.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.util.Collection;
import java.util.List;

import at.fikar.raphael.cashhelper.dal.dto.Account;
import at.fikar.raphael.cashhelper.dal.stores.AccountStore;
import at.fikar.raphael.cashhelper.logging.Log;
import at.fikar.raphael.cashhelper.logging.LogStore;
import at.fikar.raphael.cashhelper.logging.LogTypes;
import at.fikar.raphael.cashhelper.logging.Logger;
import at.fikar.raphael.cashhelper.util.XMLParser;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class FileLoader {

	private final AccountStore accountStore;
	private final FileStore fileStore;
	private final LogStore logStore;
	private BufferedReader inputReader;

	@Inject
	private FileLoader(final AccountStore accountStore, final LogStore logStore, final FileStore fileStore) {
		this.accountStore = accountStore;
		this.logStore = logStore;
		this.fileStore = fileStore;
	}

	public void loadInitFile() throws IOException {
		inputReader = getReaderForFile(fileStore.getInitFile());
		XMLParser.parseInitFile(readInitFile());
		inputReader.close();
	}

	public void loadSaveFile() throws IOException, ParseException {
		Logger.log(LogTypes.DEBUG, "loadSaveFile() called");
		inputReader = getReaderForFile(fileStore.getSaveFile());
		Logger.log(LogTypes.DEBUG, "got the reader");
		loadAccounts();
		Logger.log(LogTypes.DEBUG, "loaded accounts");
		inputReader.close();
	}

	public void loadSaveFile(final File newSaveFile) throws IOException, ParseException {
		Logger.log(LogTypes.DEBUG, "loadSaveFile(File) called");
		if (newSaveFile.exists()) {
			fileStore.setSaveFile(newSaveFile);
		} else
			throw new FileNotFoundException();
		loadSaveFile();

	}

	public void loadLogFile() throws IOException {
		inputReader = getReaderForFile(fileStore.getLogFile());
		loadLogs();
		inputReader.close();
	}

	public void loadLogFile(final File newLogFile) throws IOException, ParseException {
		if (newLogFile.exists()) {
			fileStore.setLogFile(newLogFile);
			;
		} else
			throw new FileNotFoundException();
		loadLogFile();
	}

	private BufferedReader getReaderForFile(final File inputFile) throws FileNotFoundException {
		return new BufferedReader(new FileReader(inputFile.getAbsolutePath()));
	}

	private List<String> readInitFile() throws IOException {
		final List<String> allLines = Files.readAllLines(fileStore.getInitFile().toPath());
		return allLines;
	}

	private void loadAccounts() throws IOException, ParseException {
		final List<String> allLines = Files.readAllLines(fileStore.getSaveFile().toPath());
		Logger.log(LogTypes.DEBUG, "FileReader: Read all lines from " + fileStore.getSaveFile().toPath());
		storeAccounts(allLines);
	}

	private void storeAccounts(final List<String> readLines) throws ParseException {
		final Collection<Account> newAccounts = XMLParser.convertMultipleAccounts(readLines);
		Logger.log(LogTypes.DEBUG, "FileReader: Parsed Accounts from file.");
		accountStore.putAccounts(newAccounts);
	}

	private void loadLogs() throws IOException {
		final List<String> allLines = Files.readAllLines(fileStore.getLogFile().toPath());
		Logger.log(LogTypes.DEBUG, "FileReader: Read all lines from " + fileStore.getLogFile().toPath());
		storeLogs(allLines);
	}

	private void storeLogs(final List<String> readLines) {
		final List<Log> readLogs = XMLParser.convertMultipleLogs(readLines);
		Logger.log(LogTypes.DEBUG, "FileReader: Parsed Logs from file.");
		logStore.putLoadedLogs(readLogs);
	}
}
