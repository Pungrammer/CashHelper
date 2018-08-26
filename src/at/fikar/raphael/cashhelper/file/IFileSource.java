package at.fikar.raphael.cashhelper.file;

import at.fikar.raphael.cashhelper.dal.dto.Account;
import at.fikar.raphael.cashhelper.dal.dto.Entry;

import java.io.IOException;
import java.util.Collection;

public interface IFileSource {

    /**
     * Loads the current config from the config file.
     * @return Config object, populated with config file data
     * @throws IOException If the file read fails
     */
    Config getConfig() throws IOException;

    /**
     * Overrides the current config with the given config.
     * @param config The new config to persist
     * @throws IOException If the file write fails
     */
    void setConfig(Config config) throws IOException;

    /**
     * Saves all the given accounts. Overrides all others.
     * @param accounts Accounts to save
     * @throws IOException If file write fails
     */
    void saveAccounts(Collection<Account> accounts) throws IOException;

    /**
     * Reads all accounts from the configured account save file.
     * @return Returns all accounts stored in the account save file.
     * @throws IOException If the file read fails
     */
    Collection<Account> loadAccounts() throws IOException;

    /**
     * Saves all the given entries. Overrides all others.
     * @param entries Entries to save
     * @throws IOException If file write fails
     */
    void saveEntries(Collection<Entry> entries) throws IOException;


    /**
     * Reads all entries from the configured entry save file.
     * @return Returns all entries stored in the entry save file.
     * @throws IOException If the file read fails
     */
    Collection<Entry> loadEntries() throws IOException;
}
