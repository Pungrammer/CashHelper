package at.fikar.raphael.cashhelper.gui.javafx.tableconverter;

import at.fikar.raphael.cashhelper.dal.dto.Account;
import at.fikar.raphael.cashhelper.util.TimeHandler;
import javafx.beans.property.SimpleStringProperty;

public class AccountTableDTO {
    private final SimpleStringProperty id;
    private final SimpleStringProperty name;
    private final SimpleStringProperty value;
    private final SimpleStringProperty lastEdit;
    private final SimpleStringProperty creationDate;

    public AccountTableDTO(final Account account) {
        this.id = new SimpleStringProperty(String.valueOf(account.getId().getRaw()));
        this.name = new SimpleStringProperty(account.getName());
        this.value = new SimpleStringProperty(String.valueOf(account.getValue()));
        this.lastEdit = new SimpleStringProperty(TimeHandler.millisToDateFormat(account.getLastEditAsLong()));
        this.creationDate = new SimpleStringProperty(TimeHandler.millisToDateFormat(account.getDateCreationAsLong()));
    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getValue() {
        return value.get();
    }

    public SimpleStringProperty valueProperty() {
        return value;
    }

    public void setValue(String value) {
        this.value.set(value);
    }

    public String getLastEdit() {
        return lastEdit.get();
    }

    public SimpleStringProperty lastEditProperty() {
        return lastEdit;
    }

    public void setLastEdit(String lastEdit) {
        this.lastEdit.set(lastEdit);
    }

    public String getCreationDate() {
        return creationDate.get();
    }

    public SimpleStringProperty creationDateProperty() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate.set(creationDate);
    }
}
