package edu.pitt.bank;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.pitt.utilities.DbUtilities;
import edu.pitt.utilities.MySqlUtilities;
import edu.pitt.utilities.ErrorLogger;

public class Security {

	public Customer validateLogin(String loginName, int pin){
		String sql = "SELECT * FROM customer WHERE loginName = '" + loginName + "' AND pin = '" + pin + "';";
		Customer cust = null;
		DbUtilities db = new MySqlUtilities();
		try{
			ResultSet rs = db.getResultSet(sql);
			if (rs.next()){
				cust = new Customer(rs.getString("customerID"));
			}
		} catch (SQLException e){
			ErrorLogger.log("Error in Customer validateLogin method in Security class");
			ErrorLogger.log(e.getMessage());
		}
		return cust;
		
	}
	
	public ArrayList<String> listUserGroup(String userID){
		DbUtilities db = new MySqlUtilities();
		ArrayList<String> userGroups = new ArrayList<String>();
		String sql = "SELECT user_permissions.groupID, groups.groupName "
				+ "FROM nas162_bank1017.user_permissions "
				+ "INNER JOIN groups "
				+ "ON user_permissions.groupID = groups.groupID "
				+ "WHERE groupOrUserID = '" + userID.toString() + "';";
		try{
			ResultSet rs = db.getResultSet(sql);
			while (rs.next()){
				userGroups.add(rs.getString("groupName"));
			}
		} catch (SQLException e){
			ErrorLogger.log("Error in listUserGroup method in Security class");
			ErrorLogger.log(e.getMessage());
		}
		
//		for (int i = 0; i < userGroups.size(); i++){
//			System.out.println(userGroups.get(i));
//		}
		return userGroups;
	}
}
