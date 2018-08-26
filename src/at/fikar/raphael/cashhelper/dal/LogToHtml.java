package at.fikar.raphael.cashhelper.dal;

import at.fikar.raphael.cashhelper.logging.Log;

import java.util.List;

public class LogToHtml {

	public LogToHtml() {

	}

	public void printLogs(final List<Log> logs) {
		final StringBuilder pageBuilder = new StringBuilder();
		for (final Log currentLog : logs) {
			pageBuilder.append(convertDTO(currentLog));
		}

		printPage(pageBuilder);
	}

	private String convertDTO(final Log log) {
		return "";
	}

	private void printPage(final StringBuilder pageBuilder) {

	}

}
