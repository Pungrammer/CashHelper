package at.fikar.raphael.cashhelper.dto;

import java.text.ParseException;
import java.util.Date;

import at.fikar.raphael.cashhelper.util.TimeHandler;

public class Account {

	private final String name;
	private final double value;
	private final Date lastEdit;
	private final Date creation;

	public Account(final String name, final double value, final String lastEdit, final String creation)
			throws ParseException {
		this.name = name;
		this.value = value;
		this.lastEdit = TimeHandler.timeFormatToDate(lastEdit);
		this.creation = TimeHandler.timeFormatToDate(creation);
	}

	public Account(final String name, final double value, final Date lastEdit, final Date creation) {
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
