package edu.pitt.bank;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

import edu.pitt.utilities.DbUtilities;
import edu.pitt.utilities.MySqlUtilities;
import edu.pitt.utilities.ErrorLogger;

public class Transaction {
	private String transactionID;
	private String accountID;
	private String type;
	private double amount;
	private double balance;
	@SuppressWarnings("unused")
	private Date transactionDate; 
	
	/**
	 * @param transactionID
	 */
	public Transaction(String transactionID){
		String sql = "SELECT * FROM nas162_bank1017.transaction "; 
		sql += "WHERE transactionID = '" + transactionID + "'";
		DbUtilities db = new MySqlUtilities();
		try {
			ResultSet rs = db.getResultSet(sql);
			while(rs.next()){
				this.transactionID = rs.getString("transactionID");
				this.accountID = rs.getString("accountID");
				this.type = rs.getString("type");
				this.amount = rs.getDouble("amount");
				this.balance = rs.getDouble("balance");
				this.transactionDate = new Date();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ErrorLogger.log("Error in assigning Transaction variables in Transaction class");
			ErrorLogger.log(e.getMessage());
		}
		
	}
	
	/**
	 * @param accountID
	 * @param type
	 * @param amount
	 * @param balance
	 */
	public Transaction(String accountID, String type, double amount, double balance){
		this.transactionID = UUID.randomUUID().toString();
		this.type = type;
		this.amount = amount;
		this.accountID = accountID;
		this.balance = balance;
		
		String sql = "INSERT INTO nas162_bank1017.transaction ";
		sql += "(transactionID, accountID, amount, transactionDate, type, balance) ";
		sql += " VALUES ";
		sql += "('" + this.transactionID + "', ";
		sql += "'" + this.accountID + "', ";
		sql += this.amount + ", ";
		sql += "CURDATE(), ";
		sql += "'" + this.type + "', ";
		sql += balance + ");";
		
		
		DbUtilities db = new MySqlUtilities();
		db.executeQuery(sql);
	}
	
	/**
	 * @return
	 */
	public String getTransactionID(){
		return transactionID;
	}
	
	/**
	 * @return
	 */
	public double getAmount(){
		return amount;
	}
	
	/**
	 * @return
	 */
	public String getAccountID(){
		return accountID;
	}
	
	/**
	 * @return
	 */
	public double getBalance(){
		return balance;
	}
}
