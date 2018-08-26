package at.fikar.raphael.cashhelper.dal;

public interface Persistable {

    /**
     * Persists all currently cached data.
     */
    void persist() throws Exception;
}
