package at.fikar.raphael.cashhelper.dto;

public class Log {

	private final long id;
	private String account;
	private String valueChange;
	private int newValue;
	private long timeOfInvoice;
	private long timeOfLog;
	private String reasonText;

	public Log(final long id, final String account, final String valueChange, final int newValue,
			final long timeOfInvoice, final long timeOfLog, final String reasonText) {
		super();
		this.id = id;
		this.account = account;
		this.valueChange = valueChange;
		this.newValue = newValue;
		this.timeOfInvoice = timeOfInvoice;
		this.timeOfLog = timeOfLog;
		this.reasonText = reasonText;
	}

	public long getId() {
		return id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(final String account) {
		this.account = account;
	}

	public String getValueChange() {
		return valueChange;
	}

	public void setValueChange(final String valueChange) {
		this.valueChange = valueChange;
	}

	public int getNewValue() {
		return newValue;
	}

	public void setNewValue(final int newValue) {
		this.newValue = newValue;
	}

	public long getTimeOfInvoice() {
		return timeOfInvoice;
	}

	public void setTimeOfInvoice(final long timeOfInvoice) {
		this.timeOfInvoice = timeOfInvoice;
	}

	public long getTimeOfLog() {
		return timeOfLog;
	}

	public void setTimeOfLog(final long timeOfLog) {
		this.timeOfLog = timeOfLog;
	}

	public String getReasonText() {
		return reasonText;
	}

	public void setReasonText(final String reasonText) {
		this.reasonText = reasonText;
	}

}
