package at.fikar.raphael.cashhelper.file;

import java.nio.file.Path;

public class Config {
    private Path accountSaveFileLocation;
    private Path entrySaveFileLocation;

    public Path getAccountSaveFileLocation() {
        return accountSaveFileLocation;
    }

    public void setAccountSaveFileLocation(Path accountSaveFileLocation) {
        this.accountSaveFileLocation = accountSaveFileLocation;
    }

    public Path getEntrySaveFileLocation() {
        return entrySaveFileLocation;
    }

    public void setEntrySaveFileLocation(Path entrySaveFileLocation) {
        this.entrySaveFileLocation = entrySaveFileLocation;
    }
}
