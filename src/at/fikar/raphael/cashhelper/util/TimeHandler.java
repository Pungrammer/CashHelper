package at.fikar.raphael.cashhelper.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import at.fikar.raphael.cashhelper.logging.LogTypes;
import at.fikar.raphael.cashhelper.logging.Logger;

public class TimeHandler {

	private static final String DATE_FORMAT = "yyyy.MM.dd, HH:mm:ss";
	private static final SimpleDateFormat SIMPLE_FORMAT = setupDateFormat();

	private static final String DEFAULT_DATE = "2016.01.01, 08:21:33";

	public long timeFormatToMilis(final String input) throws ParseException {
		final Date date = SIMPLE_FORMAT.parse(input);
		return date.getTime();
	}

	public static Date timeFormatToDate(final String input) throws ParseException {
		final String sanitizedInput = sanitize(input);
		return SIMPLE_FORMAT.parse(sanitizedInput);
	}

	public static String millisToDateFormat(final long millis) {
		return SIMPLE_FORMAT.format(new Date(millis));
	}

	public static long getCurrentMillis() {
		return new Date().getTime();
	}

	public static String nowAsString() {
		final long now = System.currentTimeMillis();
		return millisToDateFormat(now);
	}

	private static SimpleDateFormat setupDateFormat() {
		return new SimpleDateFormat(DATE_FORMAT);
	}

	private static String sanitize(final String input) {
		final String sanitizedInput = input.trim();
		Logger.log(LogTypes.DEBUG, "TimeHandler input: " + sanitizedInput);
		if (sanitizedInput.contains("null") || sanitizedInput == null) {
			Logger.log(LogTypes.DEBUG, "input is null");
			return DEFAULT_DATE;
		} else
			return input;
	}
}
