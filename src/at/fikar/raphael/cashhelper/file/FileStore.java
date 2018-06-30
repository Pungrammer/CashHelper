package at.fikar.raphael.cashhelper.file;

import java.io.File;

public class FileStore {

	private static final FileStore instance = new FileStore();

	private File saveFile;
	private File logFile;
	private File outputFile;

	private final File initFile = new File("init.xml");
	private final File defaultSaveFile = new File("savefile.xml");
	private final File defaultLogFile = new File("logfile.xml");
	private final File defaultOutputFile = new File("Output Log.html");

	private FileStore() {
		saveFile = defaultSaveFile;
		logFile = defaultLogFile;
		outputFile = defaultOutputFile;
	}

	public static FileStore getInstance() {
		return instance;
	}

	public File getSaveFile() {
		return saveFile;
	}

	public void setSaveFile(final File newSaveFile) {
		saveFile = newSaveFile;
	}

	public File getLogFile() {
		return logFile;
	}

	public void setLogFile(final File newLogFile) {
		logFile = newLogFile;
	}

	public File getOutputFile() {
		return outputFile;
	}

	public void setOutputFile(final File newOutputFile) {
		outputFile = newOutputFile;
	}

	public File getInitFile() {
		return initFile;
	}

}
