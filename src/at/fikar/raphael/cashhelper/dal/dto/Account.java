package at.fikar.raphael.cashhelper.dal.dto;

import at.fikar.raphael.cashhelper.util.TimeHandler;

import java.text.ParseException;
import java.util.Date;

public class Account {

	private final Id<Account> id;
	private final String name;
	private final double value;
	private final Date lastEdit;
	private final Date creation;

	public Account(final Id<Account> id, final String name, final double value, final String lastEdit, final String creation)
			throws ParseException {

		this.id = id;
		this.name = name;
		this.value = value;
		this.lastEdit = TimeHandler.timeFormatToDate(lastEdit);
		this.creation = TimeHandler.timeFormatToDate(creation);
	}

	public Account(final Id<Account> id, final String name, final double value, final Date lastEdit, final Date creation) {
		this.id = id;
		this.name = name;
		this.value = value;
		this.lastEdit = lastEdit;
		this.creation = creation;
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
