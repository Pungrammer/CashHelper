package at.fikar.raphael.cashhelper.util;

import static at.fikar.raphael.cashhelper.util.XMLTags.ACCOUNT_CLOSE;
import static at.fikar.raphael.cashhelper.util.XMLTags.ACCOUNT_OPEN;
import static at.fikar.raphael.cashhelper.util.XMLTags.CREATION_CLOSE;
import static at.fikar.raphael.cashhelper.util.XMLTags.CREATION_OPEN;
import static at.fikar.raphael.cashhelper.util.XMLTags.FIELD_CLOSE_TAG_LENGHT;
import static at.fikar.raphael.cashhelper.util.XMLTags.FIELD_OPEN_TAG_LENGTH;
import static at.fikar.raphael.cashhelper.util.XMLTags.INIT_DEFAULT_OUTPUT_OPEN;
import static at.fikar.raphael.cashhelper.util.XMLTags.INIT_LOGFILE_OPEN;
import static at.fikar.raphael.cashhelper.util.XMLTags.INIT_SAVEFILE_OPEN;
import static at.fikar.raphael.cashhelper.util.XMLTags.LASTEDIT_CLOSE;
import static at.fikar.raphael.cashhelper.util.XMLTags.LASTEDIT_OPEN;
import static at.fikar.raphael.cashhelper.util.XMLTags.LINE_SEPERATOR;
import static at.fikar.raphael.cashhelper.util.XMLTags.LOG_ACCOUNT_OPEN;
import static at.fikar.raphael.cashhelper.util.XMLTags.LOG_CLOSE;
import static at.fikar.raphael.cashhelper.util.XMLTags.LOG_NEWVALUE_OPEN;
import static at.fikar.raphael.cashhelper.util.XMLTags.LOG_OPEN;
import static at.fikar.raphael.cashhelper.util.XMLTags.LOG_REASONTEXT_OPEN;
import static at.fikar.raphael.cashhelper.util.XMLTags.LOG_TIMEINVOICE_OPEN;
import static at.fikar.raphael.cashhelper.util.XMLTags.LOG_TIMELOGGED_OPEN;
import static at.fikar.raphael.cashhelper.util.XMLTags.LOG_VALUECHANGED_OPEN;
import static at.fikar.raphael.cashhelper.util.XMLTags.NAME_CLOSE;
import static at.fikar.raphael.cashhelper.util.XMLTags.NAME_OPEN;
import static at.fikar.raphael.cashhelper.util.XMLTags.VALUE_CLOSE;
import static at.fikar.raphael.cashhelper.util.XMLTags.VALUE_OPEN;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import at.fikar.raphael.cashhelper.dto.Account;
import at.fikar.raphael.cashhelper.dto.Log;
import at.fikar.raphael.cashhelper.dto.LogStore;
import at.fikar.raphael.cashhelper.file.FileStore;
import at.fikar.raphael.cashhelper.logging.LogTypes;
import at.fikar.raphael.cashhelper.logging.Logger;

public class XMLParser {

	public static String convertAccountToXML(final Account account) {
		Logger.log(LogTypes.DEBUG, "XMLParser.cashpointToXML(Cashpoint " + account.getName() + ") called!");
		final StringBuilder builder = new StringBuilder();
		builder.append(ACCOUNT_OPEN);
		builder.append(LINE_SEPERATOR);
		builder.append(NAME_OPEN);
		builder.append(account.getName());
		builder.append(NAME_CLOSE);
		builder.append(LINE_SEPERATOR);
		builder.append(VALUE_OPEN);
		builder.append(account.getValue());
		builder.append(VALUE_CLOSE);
		builder.append(LINE_SEPERATOR);
		builder.append(LASTEDIT_OPEN);
		builder.append(account.getLastEditAsLong());
		builder.append(LASTEDIT_CLOSE);
		builder.append(LINE_SEPERATOR);
		builder.append(CREATION_OPEN);
		builder.append(account.getDateCreationAsLong());
		builder.append(CREATION_CLOSE);
		builder.append(LINE_SEPERATOR);
		builder.append(ACCOUNT_CLOSE);
		return builder.toString();
	}

	public static List<Account> convertMultipleAccounts(final List<String> input) throws ParseException {
		final List<Account> accounts = new ArrayList<Account>();
		final List<List<String>> listOfInputAccounts = splitInputIntoContainers(input, ACCOUNT_OPEN, ACCOUNT_CLOSE);
		for (int i = 0; i < listOfInputAccounts.size(); i++) {
			Logger.log(LogTypes.DEBUG, "current Account to parse: " + listOfInputAccounts.get(i));
			accounts.add(convertXMLToAccount(listOfInputAccounts.get(i)));
		}
		return accounts;
	}

	public static Account convertXMLToAccount(final List<String> input) throws ParseException {
		final List<String> cutInput = removeContainerTags(input, ACCOUNT_OPEN, ACCOUNT_CLOSE);
		String name = "";
		double value = 0;
		String lastEdit = "";
		String creation = "";

		for (final String currentLine : cutInput) {
			final String trimmedInput = removeFieldTags(currentLine);
			if (currentLine.startsWith(NAME_OPEN)) {
				name = trimmedInput;
			} else if (currentLine.startsWith(VALUE_OPEN)) {
				value = parseDouble(trimmedInput);
			} else if (currentLine.startsWith(LASTEDIT_OPEN)) {
				lastEdit = trimmedInput;
			} else if (currentLine.startsWith(CREATION_OPEN)) {
				creation = trimmedInput;
			} else if (currentLine.startsWith(ACCOUNT_OPEN) || currentLine.startsWith(ACCOUNT_CLOSE)) {
				// ignore account tags
			}
		}
		return new Account(name, value, lastEdit, creation);
	}

	public static List<Log> convertMultipleLogs(final List<String> input) {
		final List<Log> logs = new ArrayList<Log>();
		final List<List<String>> listOfInputLogs = splitInputIntoContainers(input, LOG_OPEN, LOG_CLOSE);
		for (int i = 0; i < listOfInputLogs.size(); i++) {
			Logger.log(LogTypes.DEBUG, "current Account to parse: " + listOfInputLogs.get(i));
			logs.add(convertXMLToLog(listOfInputLogs.get(i)));
		}
		return logs;
	}

	public static Log convertXMLToLog(final List<String> input) {
		final List<String> cutInput = removeContainerTags(input, LOG_OPEN, LOG_CLOSE);
		String account = "";
		String valueChange = "";
		int newValue = 0;
		long timeOfInvoice = 0L;
		long timeOfLog = 0L;
		String reasonText = "";

		for (final String currentLine : cutInput) {
			final String trimmedInput = removeFieldTags(currentLine);
			if (currentLine.startsWith(LOG_ACCOUNT_OPEN)) {
				account = trimmedInput;
			} else if (currentLine.startsWith(LOG_VALUECHANGED_OPEN)) {
				valueChange = trimmedInput;
			} else if (currentLine.startsWith(LOG_NEWVALUE_OPEN)) {
				newValue = Integer.parseInt(trimmedInput);
			} else if (currentLine.startsWith(LOG_TIMEINVOICE_OPEN)) {
				timeOfInvoice = Long.parseLong(trimmedInput);
			} else if (currentLine.startsWith(LOG_TIMELOGGED_OPEN)) {
				timeOfLog = Long.parseLong(trimmedInput);
			} else if (currentLine.startsWith(LOG_REASONTEXT_OPEN)) {
				reasonText = trimmedInput;
			}
		}
		final Log log = LogStore.getInstance().getNewLog(account, valueChange, newValue, timeOfInvoice, timeOfLog,
				reasonText);
		return log;
	}

	public static void parseInitFile(final List<String> input) {
		for (final String currentLine : input) {
			final String trimmedInput = removeFieldTags(currentLine);
			if (currentLine.startsWith(INIT_SAVEFILE_OPEN)) {
				FileStore.getInstance().setSaveFile(new File(trimmedInput));
			} else if (currentLine.startsWith(INIT_LOGFILE_OPEN)) {
				FileStore.getInstance().setLogFile(new File(trimmedInput));
			} else if (currentLine.startsWith(INIT_DEFAULT_OUTPUT_OPEN)) {
				FileStore.getInstance().setOutputFile(new File(trimmedInput));
			}
		}
	}

	public static String removeFieldTags(final String input) {
		final String output = input.substring(FIELD_OPEN_TAG_LENGTH, input.length() - FIELD_CLOSE_TAG_LENGHT);
		return output;
	}

	private static List<List<String>> splitInputIntoContainers(final List<String> rawInput,
			final String containerOpenTag, final String containerCloseTag) {
		final List<List<String>> listOfContainers = new ArrayList<List<String>>();
		for (int i = 0; i < rawInput.size(); i++) {
			if (rawInput.get(i).equals(containerOpenTag)) {
				final List<String> oneContainer = new ArrayList<String>();
				i++;
				while (!rawInput.get(i).contains(containerCloseTag)) {
					Logger.log(LogTypes.DEBUG, "rawInput.get " + rawInput.get(i));
					oneContainer.add(rawInput.get(i));
					i++;
				}
				listOfContainers.add(oneContainer);
			}
		}
		return listOfContainers;

	}

	private static List<String> removeContainerTags(final List<String> data, final String openTag,
			final String closeTag) {
		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).startsWith(openTag) || data.get(i).startsWith(closeTag)) {
				data.remove(i);
			}
		}
		return data;
	}

	private static double parseDouble(final String input) {
		try {
			return Double.parseDouble(input);
		} catch (final NumberFormatException e) {
			return Double.parseDouble(input.replace(',', '.'));
		}
	}
}
