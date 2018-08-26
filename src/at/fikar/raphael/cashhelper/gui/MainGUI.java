package at.fikar.raphael.cashhelper.gui;

import static at.fikar.raphael.cashhelper.gui.Constants.ACCOUNT_INPUT_COLUMN_X;
import static at.fikar.raphael.cashhelper.gui.Constants.ACCOUNT_INPUT_HIGHT;
import static at.fikar.raphael.cashhelper.gui.Constants.ACCOUNT_LABLE_HIGHT;
import static at.fikar.raphael.cashhelper.gui.Constants.ACCOUNT_LABLE_WIDTH;
import static at.fikar.raphael.cashhelper.gui.Constants.ACCOUNT_NAME_COLUMN_X;
import static at.fikar.raphael.cashhelper.gui.Constants.ACCOUNT_NAME_COLUMN_Y;
import static at.fikar.raphael.cashhelper.gui.Constants.ACCOUNT_VALUE_COLUMN_X;
import static at.fikar.raphael.cashhelper.gui.Constants.REASON_TEXT_HIGHT;
import static at.fikar.raphael.cashhelper.gui.Constants.REASON_TEXT_WIDTH;
import static at.fikar.raphael.cashhelper.gui.Constants.REASON_TEXT_X;
import static at.fikar.raphael.cashhelper.gui.Constants.REASON_TEXT_Y;
import static at.fikar.raphael.cashhelper.gui.Constants.SUBMIT_BUTTON_HIGHT;
import static at.fikar.raphael.cashhelper.gui.Constants.SUBMIT_BUTTON_WIDTH;
import static at.fikar.raphael.cashhelper.gui.Constants.SUBMIT_BUTTON_X;
import static at.fikar.raphael.cashhelper.gui.Constants.SUBMIT_BUTTON_Y;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import at.fikar.raphael.cashhelper.dal.dto.Account;
import at.fikar.raphael.cashhelper.dal.stores.AccountStore;
import at.fikar.raphael.cashhelper.logging.LogStore;
import at.fikar.raphael.cashhelper.file.FileLoader;
import at.fikar.raphael.cashhelper.file.FileSaver;
import at.fikar.raphael.cashhelper.logging.LogTypes;
import at.fikar.raphael.cashhelper.logging.Logger;

public class MainGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private FileLoader fileLoader;
	private FileSaver fileSaver;
	private AccountStore accountStore;
	private LogStore logStore;

	private final File initFile = new File("init.xml");

	public MainGUI() {
		setTitle("Cashhelper");
		setup();
		try {
			fileLoader.loadInitFile();
			Logger.log(LogTypes.DEBUG, "Init file loaded!");
			fileLoader.loadSaveFile();
			Logger.log(LogTypes.DEBUG, "Save file loaded!");
			fileLoader.loadLogFile();
			Logger.log(LogTypes.DEBUG, "Log file loaded!");
		} catch (final IOException e) {
			Logger.log(LogTypes.ERROR, e.toString());
		} catch (final ParseException e) {
			Logger.log(LogTypes.ERROR, "Check input files for ',' instead of '.' \n\r" + e.toString());
			e.printStackTrace();
		}
		buildGUI();
	}

	private void setup() {
		accountStore = AccountStore.getInstance();
		logStore = LogStore.getInstance();
		fileLoader = FileLoader.getInstance();
		fileSaver = new FileSaver();
	}

	private void buildGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Logger.log(LogTypes.DEBUG, "Total No of Accounts: " + accountStore.getAllAccounts().size());
		final int windowHeight = accountStore.getAllAccounts().size() * 50 + 25;
		Logger.log(LogTypes.DEBUG, "Window height: " + windowHeight);
		setBounds(100, 100, 1500, windowHeight);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		buildAccountLables();
		buildAccountValues();
		buildValueChangeInput();
		buildSubmitButton();
		buildReasonTextField();
	}

	private void buildAccountLables() {
		final List<Account> allAccounts = accountStore.getAllAccounts();
		int yOffset = 0;
		for (final Account currentAccount : allAccounts) {
			Logger.log(LogTypes.DEBUG, "Account: " + currentAccount.getName());
			Logger.log(LogTypes.DEBUG, "YOffset " + yOffset);
			final JLabel newLabel = new JLabel(currentAccount.getName());
			newLabel.setBounds(ACCOUNT_NAME_COLUMN_X, ACCOUNT_NAME_COLUMN_Y + yOffset, ACCOUNT_LABLE_WIDTH,
					ACCOUNT_LABLE_HIGHT);
			contentPane.add(newLabel);
			yOffset = yOffset + Constants.ACCOUNT_LABLE_X_SEPERATOR;

		}
		contentPane.repaint();
	}

	private void buildAccountValues() {
		final List<Account> allAccounts = accountStore.getAllAccounts();
		int yOffset = 0;
		for (final Account currentAccount : allAccounts) {
			final JLabel newLabel = new JLabel("" + currentAccount.getValue());
			newLabel.setBounds(ACCOUNT_VALUE_COLUMN_X, ACCOUNT_NAME_COLUMN_Y + yOffset, ACCOUNT_LABLE_WIDTH,
					ACCOUNT_LABLE_HIGHT);
			contentPane.add(newLabel);
			yOffset = yOffset + Constants.ACCOUNT_LABLE_X_SEPERATOR;

		}
		contentPane.repaint();
	}

	private void buildValueChangeInput() {
		final List<Account> allAccounts = accountStore.getAllAccounts();
		int yOffset = 0;
		for (int i = 0; i < allAccounts.size(); i++) {
			final JTextField newTextField = new JTextField("");
			newTextField.setBounds(ACCOUNT_INPUT_COLUMN_X, ACCOUNT_NAME_COLUMN_Y + yOffset, ACCOUNT_LABLE_WIDTH,
					ACCOUNT_INPUT_HIGHT);
			contentPane.add(newTextField);
			yOffset = yOffset + Constants.ACCOUNT_LABLE_X_SEPERATOR;
		}
		contentPane.repaint();
	}

	private void buildSubmitButton() {
		final JButton newButton = new JButton("Submit");
		newButton.setBounds(SUBMIT_BUTTON_X, SUBMIT_BUTTON_Y, SUBMIT_BUTTON_WIDTH, SUBMIT_BUTTON_HIGHT);
		newButton.addActionListener(actionEvent -> updateValue());
		contentPane.add(newButton);
		contentPane.repaint();
	}

	private void buildReasonTextField() {
		final JTextField reasonText = new JTextField();
		reasonText.setBounds(REASON_TEXT_X, REASON_TEXT_Y, REASON_TEXT_WIDTH, REASON_TEXT_HIGHT);
		contentPane.add(reasonText);
		contentPane.repaint();
	}

	private void updateValue() {
		Logger.log(LogTypes.DEBUG, "updateValues() called");
		List<Double> uiValues = getUIValues();
		//todo: implementation
	}

	private List<Double> getUIValues() {
		return null;
	}

}
