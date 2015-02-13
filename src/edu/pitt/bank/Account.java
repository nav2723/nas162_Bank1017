package edu.pitt.bank;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import edu.pitt.utilities.DbUtilities;
import edu.pitt.utilities.MySqlUtilities;
import edu.pitt.utilities.ErrorLogger;

/**
 * @author NSrivastava
 *
 */
public class Account {

	private String accountID;
	private String type;
	private double balance;
	private double interestRate;
	private double penalty;
	private String status;
	private Date dateOpen;
	private ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
	private ArrayList<Customer> accountOwners = new ArrayList<Customer>();
	
	/**
	 * @param accountID
	 */
	public Account(String accountID){
		String sql = "SELECT * FROM nas162_bank1017.account "; 
		sql += "WHERE accountID = '" + accountID + "'";
		DbUtilities db = new MySqlUtilities();
		MySqlUtilities ms = new MySqlUtilities();
		try {
			ResultSet rs = db.getResultSet(sql);
			while(rs.next()){
				this.accountID = rs.getString("accountID");
				this.type = rs.getString("type");
				this.balance = rs.getDouble("balance");
				this.interestRate = rs.getDouble("interestRate");
				this.penalty = rs.getDouble("penalty");
				this.status = rs.getString("status");
				this.dateOpen = new Date();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ErrorLogger.log("Error in assigning Account variables in Account class");
			ErrorLogger.log(e.getMessage());
		}
		ms.closeDbConnection();
		
		Transaction trans = new Transaction(this.accountID);
		transactionList.add(trans);
	}
	
	/**
	 * @param accountType
	 * @param initialBalance
	 */
	public Account(String accountType, double initialBalance){
		this.accountID = UUID.randomUUID().toString();
		this.type = accountType;
		this.balance = initialBalance;
		this.interestRate = 0;
		this.penalty = 0;
		this.status = "active";
		this.dateOpen = new Date();
		
		String sql = "INSERT INTO bank1017.account ";
		sql += "(accountID,type,balance,interestRate,penalty,status,dateOpen) ";
		sql += " VALUES ";
		sql += "('" + this.accountID + "', ";
		sql += "'" + this.type + "', ";
		sql += this.balance + ", ";
		sql += this.interestRate + ", ";
		sql += this.penalty + ", ";
		sql += "'" + this.status + "', ";
		sql += "CURDATE());";
		
		DbUtilities db = new MySqlUtilities();
		db.executeQuery(sql);
	}
	
	/**
	 * @param accountOwner
	 */
	public void addAccountOwner(Customer accountOwner){
		accountOwners.add(accountOwner);
	}
	
	/**
	 * @param amount
	 */
	public void withdraw(double amount){
		this.balance -= amount;
		createTransaction(this.accountID, "withdrawal", amount, this.balance);
		updateDatabaseAccountBalance();
	}
	
	
	/**
	 * @param amount
	 */
	public void deposit(double amount){
		this.balance += amount;
		createTransaction(this.accountID, "deposit", amount, this.balance);
		updateDatabaseAccountBalance();
	}
	
	/**
	 * 
	 */
	private void updateDatabaseAccountBalance(){
		String sql = "UPDATE nas162_bank1017.account SET balance = " + this.balance + " ";
		sql += "WHERE accountID = '" + this.accountID + "';";
		
		DbUtilities db = new MySqlUtilities();
		db.executeQuery(sql);
	}
	
	/**
	 * @param transactionID
	 * @return
	 */
	private Transaction createTransaction(String transactionID){
		Transaction t = new Transaction(transactionID);
		transactionList.add(t);
		return t;
	}
	
	/**
	 * @param accountID
	 * @param type
	 * @param amount
	 * @param balance
	 * @return
	 */
	private Transaction createTransaction(String accountID, String type, double amount, double balance){
		Transaction t = new Transaction(accountID, type, amount, balance);
		transactionList.add(t);
		return t;
	}
	
	/**
	 * @return
	 */
	public String getAccountID(){
		return this.accountID;
	}
	
	/**
	 * @return
	 */
	public double getBalance(){
		return this.balance;
	}
	
	/**
	 * @return
	 */
	public double getInterestRate(){
		return this.interestRate;
	}
	
	/**
	 * @return
	 */
	public String getStatus(){
		return this.status;
	}
	
	/**
	 * @return
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return
	 */
	public double getPenalty() {
		return penalty;
	}

	/**
	 * @param penalty
	 */
	public void setPenalty(double penalty) {
		this.penalty = penalty;
	}

	/**
	 * @return
	 */
	public Date getDateOpen() {
		return dateOpen;
	}

	/**
	 * @param dateOpen
	 */
	public void setDateOpen(Date dateOpen) {
		this.dateOpen = dateOpen;
	}

	/**
	 * @return
	 */
	public ArrayList<Transaction> getTransactionList() {
		return transactionList;
	}

	/**
	 * @param transactionList
	 */
	public void setTransactionList(ArrayList<Transaction> transactionList) {
		this.transactionList = transactionList;
	}

	/**
	 * @return
	 */
	public ArrayList<Customer> getAccountOwners() {
		return accountOwners;
	}

	/**
	 * @param accountOwners
	 */
	public void setAccountOwners(ArrayList<Customer> accountOwners) {
		this.accountOwners = accountOwners;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return this.accountID;
	}
}
