package at.fikar.raphael.cashhelper.gui.javafx.scenes;

import at.fikar.raphael.cashhelper.api.AccountAPI;
import at.fikar.raphael.cashhelper.dal.dto.Account;
import at.fikar.raphael.cashhelper.dal.dto.Id;
import at.fikar.raphael.cashhelper.gui.javafx.actionnotification.IUpdateable;
import at.fikar.raphael.cashhelper.gui.javafx.actionnotification.UpdateNotificationType;
import at.fikar.raphael.cashhelper.gui.javafx.stages.ViewEntriesStage;
import at.fikar.raphael.cashhelper.gui.javafx.tableconverter.AccountTableDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OverViewScene implements IUpdateable{


    private final Stage accountCreationStage;
    private final AccountAPI accountAPI; //TODO: Replace with IAccountStore
    private final ViewEntriesStage viewEntriesStage;

    private ObservableList<AccountTableDTO> accountTableDTOS;

    @Inject
    public OverViewScene(final Stage accountCreationStage,
                         final AccountAPI accountAPI,
                         final ViewEntriesStage viewEntriesStage) {

        this.accountCreationStage = accountCreationStage;
        this.accountAPI = accountAPI;
        this.viewEntriesStage = viewEntriesStage;

        this.accountTableDTOS = FXCollections.observableArrayList();
    }


    public Scene getScene(){
        Scene scene = new Scene(new VBox(), 800, 600);
        ((VBox)scene.getRoot()).getChildren().addAll(
                buildMenu(accountCreationStage),
                buildAccountTable()
        );

        return scene;
    }

    private MenuBar buildMenu(final Stage accountCreationStage) {
        MenuBar menuBar = new MenuBar();

        Menu menuFile = new Menu("File");

        Menu menuEdit = new Menu("Edit");
        MenuItem addAccount = new MenuItem("Add account");
        addAccount.setOnAction(event -> accountCreationStage.show());

        MenuItem deleteAccount = new MenuItem("Delete account");
        MenuItem viewAccount = new MenuItem("View account");
        viewAccount.setOnAction(event -> {
            TextInputDialog dialog = new TextInputDialog("1");
            dialog.setTitle("Show account entries dialog");
            dialog.setHeaderText("Enter the account ID");
            dialog.setContentText("ID:");

            Optional<String> result = dialog.showAndWait();
            result.ifPresent(id -> {
                Id<Account> accountId = new Id<>(Long.valueOf(id));
                Optional<Account> account = accountAPI.getAllAccounts().stream()
                        .filter(streamedAccount -> streamedAccount.getId().equals(accountId))
                        .findFirst();
                account.ifPresent(viewEntriesStage::getStage);
            });
        });

        MenuItem manageEntries = new MenuItem("Manage entries");

        menuEdit.getItems().addAll(addAccount, deleteAccount, viewAccount, manageEntries);

        menuBar.getMenus().addAll(menuFile, menuEdit);

        return menuBar;
    }

    private TableView<AccountTableDTO> buildAccountTable() {
        updateContent();
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

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(table);

        return table;
    }

    @Override
    public UpdateNotificationType getUpdateType() {
        return UpdateNotificationType.ACCOUNT;
    }

    @Override
    public void updateContent() {
        List<AccountTableDTO> tableAccounts = accountAPI.getAllAccounts().stream()
                .map(AccountTableDTO::new)
                .collect(Collectors.toList());
        accountTableDTOS = FXCollections.observableArrayList(tableAccounts);
    }
}
