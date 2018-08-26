package at.fikar.raphael.cashhelper.dal.dto;

import at.fikar.raphael.cashhelper.util.TimeHandler;

import java.text.ParseException;
import java.util.Date;

public class Account {

    private Id<Account> id;
    private String name;
    private double value;
    private Date lastEdit;
    private Date creation;

	@Deprecated
	public Account(final Id<Account> id, final String name, final double value, final String lastEdit,
				   final String creation)
			throws ParseException {
	    this(id, name, value, TimeHandler.timeFormatToDate(lastEdit), TimeHandler.timeFormatToDate(creation));
    }

	public Account(final Id<Account> id, final String name, final double value, final Date lastEdit, final Date creation) {
		this.id = id;
		this.name = name;
		this.value = value;
		this.lastEdit = lastEdit;
		this.creation = creation;
	}

    public void setId(Id<Account> id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setLastEdit(Date lastEdit) {
        this.lastEdit = lastEdit;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    public Id<Account> getId() {
        return id;
    }

    public String getName() {
		return name;
	}

	public double getValue() {
		return value;
	}

	public Date getLastEdit() {
		return lastEdit;
	}

	public long getLastEditAsLong() {
		return lastEdit.getTime();
	}

	public Date getDateCreation() {
		return creation;
	}

	public long getDateCreationAsLong() {
		return lastEdit.getTime();
	}

}
