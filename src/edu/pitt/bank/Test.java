package edu.pitt.bank;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import javax.swing.JOptionPane;

import edu.pitt.utilities.DbUtilities;
import edu.pitt.utilities.MySqlUtilities;
import edu.pitt.utilities.ErrorLogger;

@SuppressWarnings("unused")
public class Test {

	public static void main(String[] args) {

		
	//	System.out.println("Loading accounts");
		Bank b = new Bank();
		b.findAccount("2c508880-5d42-11e3-94ef-97beef767f1d");
		b.setAccountOwners();
		
		DbUtilities db = new MySqlUtilities();
		ArrayList<Account> accountList = new ArrayList<Account>();
		Map<String, Account> betterAccountList = new Hashtable<String, Account>();
		
		
		String sql = "SELECT * FROM account";
		ResultSet rs;
		try{
			rs = db.getResultSet(sql);
			while(rs.next()){
				Account a = new Account(rs.getString("accountID"));
			//	System.out.println(rs.getString("accountID"));
				accountList.add(a);
				betterAccountList.put(rs.getString("accountID"), a);
			}
		} catch(SQLException ex){
			ex.printStackTrace();
		}
		
		for(Account acct : accountList){
		//	System.out.println(acct.getBalance());
		}
		
		System.out.println("Better: " + betterAccountList.get("00ae9c2a-5d43-11e3-94ef-97beef767f1d").getBalance());
		
//		Security s = new Security();
//		s.validateLogin("nmarcus", 8125);
//		Customer c = s.validateLogin("nmarcus", 8125);
//		System.out.println(c.getLastName());
		
//		DbUtilities db = new DbUtilities();		
//		String sql = "SELECT * FROM account;";
//		try {
//			ResultSet rs = db.getResultSet(sql);
//			while(rs.next()){
//				System.out.println(rs.getString("accountID"));
//			}
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			ErrorLogger.log(e.getMessage());
//		}	
		
//		String input = JOptionPane.showInputDialog("Enter a number");
//		try{
//			int i = Integer.parseInt(input);
//		} catch(NumberFormatException e){
//			//e.printStackTrace();
//			JOptionPane.showMessageDialog(null, "You must provide numeric values. ");
//			ErrorLogger.log("Invalid Number");
//			ErrorLogger.log(e.getMessage());
//		}

	}

}
