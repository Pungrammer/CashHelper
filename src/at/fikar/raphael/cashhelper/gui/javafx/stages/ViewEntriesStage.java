package at.fikar.raphael.cashhelper.gui.javafx.stages;

import at.fikar.raphael.cashhelper.dal.dto.Account;
import at.fikar.raphael.cashhelper.dal.dto.Entry;
import at.fikar.raphael.cashhelper.dal.dto.Id;
import at.fikar.raphael.cashhelper.dal.stores.IEntryStore;
import at.fikar.raphael.cashhelper.gui.javafx.tableconverter.EntryTableDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class ViewEntriesStage {

    private final IEntryStore entryStore;
    private final AddEntryStage addEntryStage;

    @Inject
    public ViewEntriesStage(final IEntryStore entryStore,
                            final AddEntryStage addEntryStage) {
        this.entryStore = entryStore;
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
        Collection<Entry> filteredEntries = entryStore.getAllForAccount(accountId);
        Set<EntryTableDTO> convertedEntries = filteredEntries.stream().map(EntryTableDTO::new).collect(Collectors.toSet());
        ObservableList<EntryTableDTO> fxList = FXCollections.observableArrayList(convertedEntries);
        TableView<EntryTableDTO> table = new TableView<>(fxList);

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
}
