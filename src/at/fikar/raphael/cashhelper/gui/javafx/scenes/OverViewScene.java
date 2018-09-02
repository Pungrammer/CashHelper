package at.fikar.raphael.cashhelper.gui.javafx.scenes;

import at.fikar.raphael.cashhelper.api.AccountAPI;
import at.fikar.raphael.cashhelper.dal.dto.Account;
import at.fikar.raphael.cashhelper.dal.dto.Id;
import at.fikar.raphael.cashhelper.gui.javafx.actionnotification.UpdateNotificationType;
import at.fikar.raphael.cashhelper.gui.javafx.stages.ViewEntriesStage;
import at.fikar.raphael.cashhelper.gui.javafx.tableconverter.AccountTableDTO;
import at.fikar.raphael.cashhelper.gui.javafx.tables.AccountTable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.inject.Inject;
import java.util.Optional;

public class OverViewScene {


    private final Stage accountCreationStage;
    private final AccountAPI accountAPI; //TODO: Replace with IAccountStore
    private final ViewEntriesStage viewEntriesStage;
    private final AccountTable accountTable;


    @Inject
    public OverViewScene(final Stage accountCreationStage,
                         final AccountAPI accountAPI,
                         final ViewEntriesStage viewEntriesStage,
                         final AccountTable accountTable) {

        this.accountCreationStage = accountCreationStage;
        this.accountAPI = accountAPI;
        this.viewEntriesStage = viewEntriesStage;
        this.accountTable = accountTable;
    }


    public Scene getScene() {
        Scene scene = new Scene(new VBox(), 800, 600);
        ((VBox) scene.getRoot()).getChildren().addAll(
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


        TableView<AccountTableDTO> table = accountTable.build();


        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(table);

        return table;
    }

}
