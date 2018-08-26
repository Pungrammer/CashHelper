package at.fikar.raphael.cashhelper.logging;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogStore {

	private static LogStore instance = new LogStore();

	private static Map<Long, Log> storedLogs;
	private static Map<Long, Log> newLogs;

	private static long lastId;

	private LogStore() {
		storedLogs = new HashMap<>();
		newLogs = new HashMap<>();
	}

	public void putLoadedLogs(final List<Log> savedLogs) {
		final Map<Long, Log> preparedLogs = new HashMap<>();
		for (final Log currentLog : savedLogs) {
			preparedLogs.put(currentLog.getId(), currentLog);
		}
		storedLogs.putAll(preparedLogs);
	}

	public void putLog(final Log log) {
		newLogs.put(log.getId(), log);
	}

	public void putLoadedLog(final Log log) {
		storedLogs.put(log.getId(), log);
	}

	public Log getLogById(final long id) {
		if (newLogs.containsKey(id))
			return newLogs.get(id);
		else if (storedLogs.containsKey(id))
			return storedLogs.get(id);
		else
			return null;
	}

	public Log getNewLog(final String account, final String valueChange, final int newValue, final long timeOfInvoice,
			final long timeOfLog, final String reasonText) {
		return new Log(lastId++, account, valueChange, newValue, timeOfInvoice, timeOfLog, reasonText);
	}

	public static LogStore getInstance() {
		return instance;
	}
}
