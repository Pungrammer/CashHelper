package at.fikar.raphael.cashhelper.file;

import at.fikar.raphael.cashhelper.dal.dto.Account;
import at.fikar.raphael.cashhelper.dal.dto.Entry;
import com.google.common.base.Charsets;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Singleton
public class FileSource implements IFileSource {

    private final Path configFile = new File("config.json").toPath().toAbsolutePath();
    private final Path defaultAccountSaveFile = new File("accounts.json").toPath();
    private final Path defaultEntrySaveFile = new File("entries.json").toPath();
    private final Gson gson;

    private Config config;

    @Inject
    public FileSource(final Gson gson) {
        this.gson = gson;
    }

    @Override
    public Config getConfig() throws IOException {
        if (config == null) {
            config = readAllLinesAndConvert(configFile, Config.class);
            if (config.getAccountSaveFileLocation() == null) {
                config.setAccountSaveFileLocation(defaultAccountSaveFile);
                config.setEntrySaveFileLocation(defaultEntrySaveFile);
            }

            if (!config.getAccountSaveFileLocation().toFile().exists()) {
                Files.createFile(config.getAccountSaveFileLocation());
            }
            if (!config.getEntrySaveFileLocation().toFile().exists()) {
                Files.createFile(config.getEntrySaveFileLocation());
            }
        }

        return config;
    }

    @Override
    public void setConfig(final Config config) throws IOException {
        this.config = config;
        String json = gson.toJson(config);
        overwriteFile(configFile, json);
    }

    @Override
    public void saveAccounts(final Collection<Account> accounts) throws IOException {
        String json = gson.toJson(accounts);
        overwriteFile(getConfig().getAccountSaveFileLocation(), json);
    }

    @Override
    public Collection<Account> loadAccounts() throws IOException {
        TypeToken<Collection<Account>> token = new TypeToken<>() {
        };
        Collection<Account> loadedAccounts = readAllLinesAndConvert(getConfig().getAccountSaveFileLocation(), token.getType());
        return loadedAccounts == null ? new ArrayList<>() : loadedAccounts;
    }

    @Override
    public void saveEntries(final Collection<Entry> entries) throws IOException {
        String json = gson.toJson(entries);
        overwriteFile(getConfig().getEntrySaveFileLocation(), json);
    }

    @Override
    public Collection<Entry> loadEntries() throws IOException {
        TypeToken<Collection<Entry>> token = new TypeToken<>() {
        };
        Collection<Entry> loadedEntries = readAllLinesAndConvert(getConfig().getEntrySaveFileLocation(), token.getType());
        return loadedEntries == null ? new ArrayList<>() : loadedEntries;
    }

    private void overwriteFile(final Path path, final String content) throws IOException {
        Files.write(path,
                content.getBytes(Charsets.UTF_8),
                StandardOpenOption.CREATE);
    }

    private <T> T readAllLinesAndConvert(final Path path, final Type targetType) throws IOException {
        List<String> strings = Files.readAllLines(path);
        final StringBuilder configJsonBuilder = new StringBuilder();
        strings.forEach(configJsonBuilder::append);
        return gson.fromJson(configJsonBuilder.toString(), targetType);
    }

}
