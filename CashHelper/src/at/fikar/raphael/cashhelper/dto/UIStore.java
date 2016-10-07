package at.fikar.raphael.cashhelper.dto;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class UIStore {

	private static UIStore instance = new UIStore();

	private List<Account> accountList;
	private List<JLabel> nameLabelList;
	private List<JLabel> valueLabelList;
	private List<JTextField> inputFieldList;

	private UIStore() {
		accountList = new ArrayList<Account>();
		nameLabelList = new ArrayList<JLabel>();
		valueLabelList = new ArrayList<JLabel>();
		inputFieldList = new ArrayList<JTextField>();
	}

	public static UIStore getInstance() {
		return instance;
	}

	public void addAccount(final Account account) {
		accountList.add(account);
	}

	public void addNameLabel(final Account account, final JLabel nameLabel) {
		nameLabelList.add(nameLabel);
	}

	public void addValueLabel(final Account account, final JLabel valueLabel) {
		valueLabelList.add(valueLabel);
	}

	public void addInputField(final Account account, final JTextField inputField) {
		inputFieldList.add(inputField);
	}

	public Account getAccountByInputField(final JTextField inputField) {
		return null;
	}

}
