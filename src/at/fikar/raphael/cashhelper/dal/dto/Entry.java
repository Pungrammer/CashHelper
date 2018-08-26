package at.fikar.raphael.cashhelper.dal.dto;

import java.time.LocalDateTime;

public class Entry {

    private Id<Entry> id;
    private double value;
    private LocalDateTime date;
    private String issuer; //The person giving money or removing it
    private String comment;

    public Entry(Id<Entry> id, double value, LocalDateTime date, String issuer, String comment) {
        this.id = id;
        this.value = value;
        this.date = date;
        this.issuer = issuer;
        this.comment = comment;
    }

    public Id<Entry> getId() {
        return id;
    }

    public void setId(Id<Entry> id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
