package at.fikar.raphael.cashhelper.logging;

import java.io.PrintStream;

public class Logger {

	private static final String ERROR_PREFIX = "ERROR: ";
	private static final String DEBUG_PREFIX = "DEBUG: ";
	private static final String WARNING_PREFIX = "WARNING: ";
	private static final String STATUS_PREFIX = "STATUS: ";

	private static PrintStream outStream = System.out;

	private static boolean debugFlag = true;

	public static void log(final LogTypes logtype, final String inputMessage) {

		String message = "";

		switch (logtype) {
		case ERROR:
			message = ERROR_PREFIX + inputMessage;
			break;
		case DEBUG:
			if (debugFlag) {
				message = DEBUG_PREFIX + inputMessage;
			}
			break;
		case WARNING:
			message = WARNING_PREFIX + inputMessage;
			break;
		case STATUS:
			message = STATUS_PREFIX + inputMessage;
			break;
		default:
			break;
		}
		print(message);
	}

	private static void print(final String message) {
		outStream.println(message);
	}
}
