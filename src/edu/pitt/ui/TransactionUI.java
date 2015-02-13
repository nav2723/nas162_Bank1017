package edu.pitt.ui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

import edu.pitt.bank.Account;
import edu.pitt.utilities.DbUtilities;
import edu.pitt.utilities.ErrorLogger;
import edu.pitt.utilities.MySqlUtilities;

public class TransactionUI {

	private JFrame transactionFrame;
	private JScrollPane transactionPane;
	private JTable tblTransactions;
	private Account currentAccount;



	/**
	 * Create the application.
	 */
	public TransactionUI(Account a) {
		currentAccount = a;
		initialize();
		transactionFrame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		transactionFrame = new JFrame();
		transactionFrame.setTitle("Account Transactions: " + currentAccount.getAccountID());
		transactionFrame.setBounds(100, 100, 450, 300);
		transactionFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane();
		transactionFrame.getContentPane().add(scrollPane, BorderLayout.NORTH);
		
		transactionPane = new JScrollPane();
		transactionFrame.getContentPane().add(transactionPane, BorderLayout.CENTER);
		DbUtilities db = new MySqlUtilities();
		String[] cols = {"Transaction ID", "Date", "Amount"};
		String sql = "SELECT transactionID, transactionDate, amount "
				+ "FROM transaction "
				+ "WHERE accountID = '" + currentAccount.getAccountID() + "' ORDER BY transactionDate DESC;"; 
		try {
			DefaultTableModel transactionList = db.getDataTable(sql, cols);
			tblTransactions = new JTable(transactionList);
			tblTransactions.setFillsViewportHeight(true);
			tblTransactions.setShowGrid(true);
			tblTransactions.setGridColor(Color.black);
			transactionPane.setViewportView(tblTransactions);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ErrorLogger.log("Error in initializing TransactionUI frame");
			ErrorLogger.log(e.getMessage());
		}
		
		
	}
}
