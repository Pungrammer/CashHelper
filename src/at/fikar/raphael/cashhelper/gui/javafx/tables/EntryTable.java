package at.fikar.raphael.cashhelper.gui.javafx.tables;

import at.fikar.raphael.cashhelper.dal.dto.Account;
import at.fikar.raphael.cashhelper.dal.dto.Entry;
import at.fikar.raphael.cashhelper.dal.dto.Id;
import at.fikar.raphael.cashhelper.dal.stores.IEntryStore;
import at.fikar.raphael.cashhelper.gui.javafx.IBuildable;
import at.fikar.raphael.cashhelper.gui.javafx.actionnotification.IUpdateable;
import at.fikar.raphael.cashhelper.gui.javafx.actionnotification.UpdateNotificationType;
import at.fikar.raphael.cashhelper.gui.javafx.actionnotification.UpdateNotifier;
import at.fikar.raphael.cashhelper.gui.javafx.tableconverter.EntryTableDTO;
import com.google.inject.assistedinject.Assisted;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class EntryTable implements IUpdateable, IBuildable<TableView<EntryTableDTO>> {

    private final IEntryStore entryStore;
    private final Id<Account> accountId;

    private ObservableList<EntryTableDTO> entryTableDTOS;

    @Inject
    public EntryTable(final UpdateNotifier updateNotifier,
                      final IEntryStore entryStore,
                      @Assisted final Id<Account> accountId)
    {
        this.accountId = accountId;
        this.entryStore = entryStore;

        updateNotifier.subscribeListener(this);

        entryTableDTOS = FXCollections.observableArrayList();
    }

    @Override
    public TableView<EntryTableDTO> build() {
        updateContent();

        TableView<EntryTableDTO> table = new TableView<>(entryTableDTOS);

        TableColumn<EntryTableDTO, String> id = new TableColumn<>("Id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<EntryTableDTO, String> value = new TableColumn<>("Value");
        value.setCellValueFactory(new PropertyValueFactory<>("value"));

        TableColumn<EntryTableDTO, String> date = new TableColumn<>("Date");
        date.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<EntryTableDTO, String> issuer = new TableColumn<>("Issuer");
        issuer.setCellValueFactory(new PropertyValueFactory<>("issuer"));

        TableColumn<EntryTableDTO, String> comment = new TableColumn<>("Comment");
        comment.setCellValueFactory(new PropertyValueFactory<>("comment"));

        table.getColumns().add(id);
        table.getColumns().add(value);
        table.getColumns().add(date);
        table.getColumns().add(issuer);
        table.getColumns().add(comment);

        table.setEditable(false);
        return table;
    }

    @Override
    public UpdateNotificationType getUpdateType() {
        return UpdateNotificationType.ENTRY;
    }

    @Override
    public void updateContent() {
        Collection<Entry> filteredEntries = entryStore.getAllForAccount(accountId);
        Set<EntryTableDTO> convertedEntries = filteredEntries.stream().map(EntryTableDTO::new).collect(Collectors.toSet());
        ObservableList<EntryTableDTO> entryTableDTOS = FXCollections.observableArrayList(convertedEntries);
    }
}
