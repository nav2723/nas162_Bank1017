package edu.pitt.bank;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.pitt.utilities.DbUtilities;
import edu.pitt.utilities.MySqlUtilities;
import edu.pitt.utilities.ErrorLogger;


public class Bank {
	public ArrayList<Account> accountList = new ArrayList<Account>();
	public ArrayList<Customer> customerList = new ArrayList<Customer>();
	public Account acc = null;
	

	public Bank(){
	
		loadAccounts();
		//setAccountOwners();
	}

	
	public void loadAccounts(){
		
		String sql = "SELECT accountID FROM account;";
		
		DbUtilities db = new MySqlUtilities();
		try {
			ResultSet rs = db.getResultSet(sql);
			while(rs.next()){
				acc = new Account(rs.getString("accountID"));
				accountList.add(acc);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ErrorLogger.log("Error in loadAccounts method of the Bank class");
			ErrorLogger.log(e.getMessage());
			
		}

	}
	
	public Account findAccount(String accountID){
		Account foundAccount = null;
		for (int i=0; i < accountList.size(); i++){
			if (accountList.get(i).getAccountID().equalsIgnoreCase(accountID)){
				System.out.println("Found account");
				return accountList.get(i);
			} 
		}
		return null;
		
	}
	
	public Customer findCustomer(String customer){
		Customer foundCustomer = null;
		for (int i=0; i < customerList.size(); i++){
			if (customerList.get(i).toString().contains(customer)){
				System.out.println("Found customer");
				foundCustomer = new Customer(customer);
				break;
			}
		}
		return foundCustomer;
		
	}
	
	public void setAccountOwners(){
		
		String sql = "SELECT fk_accountID, fk_customerID FROM account_customer;";
		Customer cust = null;
		DbUtilities db = new MySqlUtilities();
		try {
			ResultSet rs = db.getResultSet(sql);
			while(rs.next()){
				cust = new Customer(rs.getString("fk_customerID"));
				customerList.add(cust);
				Account a = this.findAccount(rs.getString("fk_accountID"));
				a.addAccountOwner(cust);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ErrorLogger.log("Error in setAccountOwners method of the Bank class");
			ErrorLogger.log(e.getMessage());
			
		}
		for (int i=0; i < customerList.size(); i++){
			System.out.println("Customerlist " + customerList.get(i).getCustomerID());
		}
		
//Add each Customer object to accountOwners property of Account class
//Use addAccountOwner method of Account class
//		loadAccounts();
//		for (int i = 0; i < customerList.size(); i++){
//			acc.addAccountOwner(customerList.get(i));
//		}
	
		
		
	}
	
	
}
