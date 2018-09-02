package at.fikar.raphael.cashhelper.injection.factories;

import at.fikar.raphael.cashhelper.dal.dto.Account;
import at.fikar.raphael.cashhelper.dal.dto.Id;
import at.fikar.raphael.cashhelper.gui.javafx.tables.EntryTable;

public interface IEntryTableFactory {

    EntryTable create(Id<Account> accountId);
}
