package at.fikar.raphael.cashhelper.logging;

import java.io.PrintStream;

public class Logger {

	private static final PrintStream OUT_STREAM = System.out;

	private static boolean debugFlag = true;

	public static void log(final LogTypes logtype, final String inputMessage) {

		if(logtype==LogTypes.DEBUG && !debugFlag){
			return;
		}

		String message = logtype.name()+inputMessage;

		print(message);
	}

	private static void print(final String message) {
		OUT_STREAM.println(message);
	}
}
