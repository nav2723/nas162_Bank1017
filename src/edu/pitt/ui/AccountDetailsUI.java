package edu.pitt.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JComboBox;

import edu.pitt.bank.Account;
import edu.pitt.bank.Customer;
import edu.pitt.bank.Security;
import edu.pitt.bank.Transaction;
import edu.pitt.utilities.DbUtilities;
import edu.pitt.utilities.MySqlUtilities;
import edu.pitt.utilities.ErrorLogger;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingUtilities;


public class AccountDetailsUI {

	private JFrame accountDetailsFrame;
	JComboBox cboAccounts;
	private Customer accountOwner;
	JLabel lblAccountTypeTxt;
	JLabel lblAccountType;
	JLabel lblBalanceTxt;
	JLabel lblBalance;
	JLabel lblInterestRateTxt;
	JLabel lblInterestRate;
	JLabel lblPenaltyTxt;
	JLabel lblPenalty;
	JLabel lblHeader;
	private JLabel lblAmountTxt;
	private JTextField textField;
	private JTextField txtAmount;


	/**
	 * Create the application.
	 */
	/**
	 * @param c
	 */
	public AccountDetailsUI(Customer c) {
		accountOwner = c;
		initialize();
		accountDetailsFrame.setVisible(true);
	}

	/**
	 * Initialize the contents of the accountDetailsFrame.
	 */
	private void initialize() {
		accountDetailsFrame = new JFrame();
		accountDetailsFrame.setBounds(100, 100, 450, 300);
		accountDetailsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		accountDetailsFrame.getContentPane().setLayout(null);

		lblAccountTypeTxt = new JLabel("Account Type:");
		lblAccountTypeTxt.setBounds(6, 118, 90, 16);
		accountDetailsFrame.getContentPane().add(lblAccountTypeTxt);

		lblAccountType = new JLabel("");
		lblAccountType.setBounds(108, 118, 125, 16);
		accountDetailsFrame.getContentPane().add(lblAccountType);


		lblAccountTypeTxt = new JLabel("Account Type:");
		lblAccountTypeTxt.setBounds(6, 118, 90, 16);
		accountDetailsFrame.getContentPane().add(lblAccountTypeTxt);

		lblAccountType = new JLabel("");
		lblAccountType.setBounds(108, 118, 61, 16);
		accountDetailsFrame.getContentPane().add(lblAccountType);

		lblBalanceTxt = new JLabel("Balance:");
		lblBalanceTxt.setBounds(6, 135, 61, 16);
		accountDetailsFrame.getContentPane().add(lblBalanceTxt);

		lblBalance = new JLabel("");
		lblBalance.setBounds(108, 135, 125, 16);
		accountDetailsFrame.getContentPane().add(lblBalance);

		lblInterestRateTxt = new JLabel("Interest Rate:");
		lblInterestRateTxt.setBounds(6, 152, 90, 16);
		accountDetailsFrame.getContentPane().add(lblInterestRateTxt);

		lblInterestRate = new JLabel("");
		lblInterestRate.setBounds(108, 152, 125, 16);
		accountDetailsFrame.getContentPane().add(lblInterestRate);

		lblPenaltyTxt = new JLabel("Penalty:");
		lblPenaltyTxt.setBounds(6, 170, 61, 16);
		accountDetailsFrame.getContentPane().add(lblPenaltyTxt);

		lblPenalty = new JLabel("");
		lblPenalty.setBounds(108, 170, 125, 16);
		accountDetailsFrame.getContentPane().add(lblPenalty);

		lblHeader = new JLabel("");
		lblHeader.setVerticalAlignment(SwingConstants.TOP);
		lblHeader.setBounds(6, 6, 438, 50);
		accountDetailsFrame.getContentPane().add(lblHeader);


		cboAccounts = new JComboBox();


		cboAccounts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				Security s = new Security();
				Account selectedAccount = (Account) cboAccounts.getSelectedItem();

				lblAccountType.setText(selectedAccount.getType());

				Double balanceValue = selectedAccount.getBalance();
				lblBalance.setText("$" + Double.toString(balanceValue));

				Double interestRateValue = selectedAccount.getInterestRate();
				lblInterestRate.setText(Double.toString(interestRateValue) + "%");

				Double penaltyValue = selectedAccount.getPenalty();
				lblPenalty.setText("$" + Double.toString(penaltyValue));

				String userGroupString = s.listUserGroup(accountOwner.getCustomerID().toString()).toString();
				lblHeader.setText("<html>"+accountOwner.getFirstName().toString() + " " + accountOwner.getLastName().toString() + ", welcome to 1017 Bank."
						+ "You have the following permissions in this system: " + userGroupString.substring(1, userGroupString.length()-1) + "</html>");

			}
		});

		cboAccounts.setBounds(60, 56, 333, 50);
		accountDetailsFrame.getContentPane().add(cboAccounts);

		lblAmountTxt = new JLabel("Amount:");
		lblAmountTxt.setBounds(223, 135, 61, 16);
		accountDetailsFrame.getContentPane().add(lblAmountTxt);

		txtAmount = new JTextField();
		txtAmount.setBounds(294, 135, 99, 16);
		accountDetailsFrame.getContentPane().add(txtAmount);
		txtAmount.setColumns(10);

		JButton btnDeposit = new JButton("Deposit");
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Account currentAccount = (Account) cboAccounts.getSelectedItem();
				try {
					Double.parseDouble(txtAmount.getText());

					currentAccount.deposit(Double.parseDouble(txtAmount.getText()));
					JOptionPane.showMessageDialog(null, "$" + txtAmount.getText() + " has been deposited.");
					txtAmount.setText("");
					Double balanceValue = currentAccount.getBalance();
					lblBalance.setText("$" + Double.toString(balanceValue));
				}
				catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "You must enter a numeric value.");
					ErrorLogger.log("User did not enter a numeric value into the Amount box: " + txtAmount.getText());
					ErrorLogger.log(ex.getMessage());
				}
			}
		});
		btnDeposit.setBounds(211, 165, 99, 29);
		accountDetailsFrame.getContentPane().add(btnDeposit);

		JButton btnWithdraw = new JButton("Withdraw");
		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Account currentAccount = (Account) cboAccounts.getSelectedItem();
				try{
					Double amount = Double.parseDouble(txtAmount.getText());
					if (amount > currentAccount.getBalance()){
						JOptionPane.showMessageDialog(null, "You cannot withdraw more than you have in your account");
					} else {
						currentAccount.withdraw(Double.parseDouble(txtAmount.getText()));
						JOptionPane.showMessageDialog(null, "$" + txtAmount.getText() + " has been withdrawn.");
						txtAmount.setText("");
						Double balanceValue = currentAccount.getBalance();
						lblBalance.setText("$" + Double.toString(balanceValue));
					}
				}
				catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "You must enter a numeric value.");
					ErrorLogger.log("User did not enter a numeric value into the Amount box: " + txtAmount.getText());
					ErrorLogger.log(ex.getMessage());
				}
			}
		});
		btnWithdraw.setBounds(322, 165, 99, 29);
		accountDetailsFrame.getContentPane().add(btnWithdraw);

		JButton btnShowTransaction = new JButton("Show Transactions");
		btnShowTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				TransactionUI t = new TransactionUI((Account) cboAccounts.getSelectedItem());	

			}
		});
		btnShowTransaction.setBounds(138, 215, 156, 29);
		accountDetailsFrame.getContentPane().add(btnShowTransaction);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(327, 215, 117, 29);
		accountDetailsFrame.getContentPane().add(btnExit);




		DbUtilities db = new MySqlUtilities();
		String sql = "SELECT * FROM customer JOIN customer_account ON customerId = "
				+ "fk_customerId JOIN account ON fk_accountId = accountId "
				+ "WHERE customerId = '" + accountOwner.getCustomerID() + "';";
		try{
			ResultSet rs = db.getResultSet(sql);
			while(rs.next()){
				Account acct = new Account(rs.getString("fk_accountID"));
				cboAccounts.addItem(acct);
			}
		} catch (SQLException e){
			e.printStackTrace();
			ErrorLogger.log("Error in adding Accounts to the comboBox in AccountDetailsUI");
			ErrorLogger.log(e.getMessage());
		}
	}
}

