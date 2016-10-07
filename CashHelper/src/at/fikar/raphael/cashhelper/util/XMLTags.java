package at.fikar.raphael.cashhelper.util;

public interface XMLTags {

	public static final String ACCOUNT_OPEN = "<ac>";
	public static final String ACCOUNT_CLOSE = "</ac>";
	public static final String NAME_OPEN = "<n>";
	public static final String NAME_CLOSE = "</n>";
	public static final String VALUE_OPEN = "<v>";
	public static final String VALUE_CLOSE = "</v>";
	public static final String LASTEDIT_OPEN = "<l>";
	public static final String LASTEDIT_CLOSE = "</l>";
	public static final String CREATION_OPEN = "<c>";
	public static final String CREATION_CLOSE = "</c>";

	public static final String LINE_SEPERATOR = System.lineSeparator();

	public static final String INIT_SAVEFILE_OPEN = "<s>";
	public static final String INIT_SAVEFILE_CLOSE = "</s>";
	public static final String INIT_LOGFILE_OPEN = "<l>";
	public static final String INIT_LOGFILE_CLOSE = "</l>";
	public static final String INIT_DEFAULT_OUTPUT_OPEN = "<o>";
	public static final String INIT_DEFAULT_OUTPUT_CLOSE = "</o>";

	public static final String LOG_OPEN = "<lo>";
	public static final String LOG_CLOSE = "</lo>";
	public static final String LOG_ACCOUNT_OPEN = "<a>";
	public static final String LOG_ACCOUNT_CLOSE = "</a>";
	public static final String LOG_VALUECHANGED_OPEN = "<v>";
	public static final String LOG_VALUECHANGED_CLOSE = "</v>";
	public static final String LOG_NEWVALUE_OPEN = "<n>";
	public static final String LOG_NEWVALUE_CLOSE = "</n>";
	public static final String LOG_TIMEINVOICE_OPEN = "<t>";
	public static final String LOG_TIME_CLOSE = "</t>";
	public static final String LOG_TIMELOGGED_OPEN = "<i>";
	public static final String LOG_TIMELOGGED_CLOSE = "</i>";
	public static final String LOG_REASONTEXT_OPEN = "<r>";
	public static final String LOG_REASONTEXT_CLOSE = "</r>";

	public static final int FIELD_OPEN_TAG_LENGTH = NAME_OPEN.length();
	public static final int FIELD_CLOSE_TAG_LENGHT = NAME_CLOSE.length();

	public static final int CONTAINER_OPEN_TAG_LENGTH = ACCOUNT_OPEN.length();
	public static final int CONTAINER_CLOSE_TAG_LENGTH = ACCOUNT_CLOSE.length();

}
