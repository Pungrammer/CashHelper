package at.fikar.raphael.cashhelper.gui.javafx.tables;

import at.fikar.raphael.cashhelper.dal.stores.IAccountStore;
import at.fikar.raphael.cashhelper.gui.javafx.IBuildable;
import at.fikar.raphael.cashhelper.gui.javafx.actionnotification.IUpdateable;
import at.fikar.raphael.cashhelper.gui.javafx.actionnotification.UpdateNotificationType;
import at.fikar.raphael.cashhelper.gui.javafx.actionnotification.UpdateNotifier;
import at.fikar.raphael.cashhelper.gui.javafx.tableconverter.AccountTableDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class AccountTable implements IUpdateable, IBuildable<TableView<AccountTableDTO>> {

    private final IAccountStore accountStore;
    private ObservableList<AccountTableDTO> accountTableDTOS;


    @Inject
    public AccountTable(final UpdateNotifier updateNotifier, final IAccountStore accountStore) {
        this.accountStore = accountStore;
        this.accountTableDTOS = FXCollections.observableArrayList();

        updateNotifier.subscribeListener(this);
    }

    @Override
    public TableView<AccountTableDTO> build() {
        TableView<AccountTableDTO> table = new TableView<>(accountTableDTOS);

        TableColumn<AccountTableDTO, String> id = new TableColumn<>("Id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<AccountTableDTO, String> name = new TableColumn<>("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<AccountTableDTO, String> value = new TableColumn<>("Value");
        value.setCellValueFactory(new PropertyValueFactory<>("value"));

        TableColumn<AccountTableDTO, String> createdAt = new TableColumn<>("Created at");
        createdAt.setCellValueFactory(new PropertyValueFactory<>("creationDate"));

        TableColumn<AccountTableDTO, String> lastModified = new TableColumn<>("Last Modified");
        lastModified.setCellValueFactory(new PropertyValueFactory<>("lastEdit"));

        table.getColumns().add(id);
        table.getColumns().add(name);
        table.getColumns().add(value);
        table.getColumns().add(createdAt);
        table.getColumns().add(lastModified);

        table.setEditable(false);
        return table;
    }

    @Override
    public UpdateNotificationType getUpdateType() {
        return UpdateNotificationType.ACCOUNT;
    }

    @Override
    public void updateContent() {
        List<AccountTableDTO> tableAccounts = accountStore.getAll().stream()
                .map(AccountTableDTO::new)
                .collect(Collectors.toList());
        accountTableDTOS = FXCollections.observableArrayList(tableAccounts);
    }
}
