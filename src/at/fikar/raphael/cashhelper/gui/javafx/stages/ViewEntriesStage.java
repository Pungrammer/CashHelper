package at.fikar.raphael.cashhelper.gui.javafx.stages;

import at.fikar.raphael.cashhelper.dal.dto.Account;
import at.fikar.raphael.cashhelper.dal.dto.Id;
import at.fikar.raphael.cashhelper.gui.javafx.tableconverter.EntryTableDTO;
import at.fikar.raphael.cashhelper.gui.javafx.tables.EntryTable;
import at.fikar.raphael.cashhelper.injection.factories.IEntryTableFactory;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.inject.Inject;

public class ViewEntriesStage {

    private final IEntryTableFactory entryTableFactory;
    private final AddEntryStage addEntryStage;

    @Inject
    public ViewEntriesStage(final IEntryTableFactory entryTableFactory,
                            final AddEntryStage addEntryStage) {
        this.entryTableFactory = entryTableFactory;
        this.addEntryStage = addEntryStage;
    }

    public Stage getStage(final Account account) {
        Stage stage = new Stage();

        buildMenu(addEntryStage);
        TableView<EntryTableDTO> table = buildTable(account.getId());

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(table);

        Scene scene = new Scene(vbox, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Account " + account.getName());

        return stage;
    }

    private MenuBar buildMenu(final AddEntryStage addEntryStage) {
        MenuBar menuBar = new MenuBar();

        Menu menuFile = new Menu("Add");
        menuFile.setOnAction(event -> addEntryStage.getStage().show());

        Menu menuEdit = new Menu("Edit");
        menuEdit.setOnAction(event -> {

        });

        Menu menuDelete = new Menu("Delete");
        menuDelete.setOnAction(event -> {

        });

        menuBar.getMenus().addAll(menuFile, menuEdit, menuDelete);

        return menuBar;
    }

    private TableView<EntryTableDTO> buildTable(Id<Account> accountId) {
        EntryTable entryTable = entryTableFactory.create(accountId);
        return entryTable.build();
    }
}
