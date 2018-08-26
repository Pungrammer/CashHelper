package at.fikar.raphael.cashhelper.gui.javafx.tableconverter;

import at.fikar.raphael.cashhelper.dal.dto.Entry;
import at.fikar.raphael.cashhelper.util.TimeHandler;
import javafx.beans.property.SimpleStringProperty;

import java.time.ZoneOffset;

public class EntryTableDTO {
    private final SimpleStringProperty id;
    private final SimpleStringProperty value;
    private final SimpleStringProperty date;
    private final SimpleStringProperty issuer;
    private final SimpleStringProperty comment;

    public EntryTableDTO(final Entry entry) {
        id = new SimpleStringProperty(String.valueOf(entry.getId().getRaw()));
        value = new SimpleStringProperty(String.valueOf(entry.getValue()));
        date = new SimpleStringProperty(TimeHandler.millisToDateFormat(entry.getDate().toInstant(ZoneOffset.UTC).toEpochMilli()));
        issuer = new SimpleStringProperty(entry.getIssuer());
        comment = new SimpleStringProperty(entry.getComment());
    }
}
