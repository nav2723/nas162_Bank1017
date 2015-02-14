package edu.pitt.bank;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import edu.pitt.utilities.DbUtilities;
import edu.pitt.utilities.MySqlUtilities;
import edu.pitt.utilities.ErrorLogger;

/**
 * @author NSrivastava
 *
 */
public class Customer {
	private String customerID;
	private String firstName;
	private String lastName;
	private String ssn;
	private String streetAddress;
	private String city;
	private String state;
	private int zip;
	private String loginName;
	private int pin;
	DbUtilities db = new MySqlUtilities();
	
	/**
	 * @param customerID
	 */
	public Customer(String customerID){
		String sql = "SELECT * FROM nas162_bank1017.customer "; 
		sql += "WHERE customerID = '" + customerID + "'";
		
		try {
			ResultSet rs = db.getResultSet(sql);
			while(rs.next()){
				this.customerID = rs.getString("customerID");
				this.firstName = rs.getString("firstName");
				this.lastName = rs.getString("lastName");
				this.ssn = rs.getString("ssn");
				this.streetAddress = rs.getString("streetAddress");
				this.city = rs.getString("city");
				this.state = rs.getString("state");
				this.zip = rs.getInt("zip");
				this.loginName = rs.getString("loginName");
				this.pin = rs.getInt("pin");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ErrorLogger.log("Error in assigning Customer variables in Customer class");
			ErrorLogger.log(e.getMessage());
		}
	}
	
	/**
	 * @param lastName
	 * @param firstName
	 * @param ssn
	 * @param loginName
	 * @param pin
	 * @param streetAddress
	 * @param city
	 * @param state
	 * @param zip
	 */
	public Customer(String lastName, String firstName, String ssn, String loginName, int pin, String streetAddress, String city, String state, int zip){
		this.customerID = UUID.randomUUID().toString();
		this.firstName = firstName;
		this.lastName = lastName;
		this.ssn = ssn;
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.loginName = loginName;
		this.pin = pin;
		
		
		String sql = "INSERT INTO nas162_bank1017.customer ";
		sql += "(customerID,firstName,lastName,ssn,streetAddress,city,state,zip,loginName,pin) ";
		sql += " VALUES ";
		sql += "('" + this.customerID + "', ";
		sql += "'" + this.firstName + "', ";
		sql += "'" + this.lastName + "', ";
		sql += "'" + this.ssn + "', ";
		sql += "'" + this.streetAddress + "', ";
		sql += "'" + this.city + "', ";
		sql += "'" + this.state + "', ";
		sql += "'" + this.zip + "', ";
		sql += "'" + this.loginName + "', ";
		sql += "'" + this.pin + "'); ";
		System.out.println(sql);
		
		DbUtilities db = new MySqlUtilities();
		db.executeQuery(sql);
	}
	
	/**
	 * @return
	 */
	public String getCustomerID(){
		return customerID;
	}
	
	/**
	 * @return
	 */
	public String getStreetAddress(){
		return streetAddress;
	}
	
	/**
	 * @return
	 */
	public String getCity(){
		return city;
	}
	
	/**
	 * @return
	 */
	public String getState(){
		return state;
	}
	
	/**
	 * @return
	 */
	public String getLoginName(){
		return loginName;
	}
	
	/**
	 * @return
	 */
	public String getFirstName(){
		return firstName;
	}
	
	/**
	 * @return
	 */
	public String getLastName(){
		return lastName;
	}
	/**
	 * @param streetAddress
	 */
	public void setStreetAddress(String streetAddress){
		this.streetAddress = streetAddress;
	}
	 
	/**
	 * @param city
	 */
	public void setCity(String city){
		this.city = city;
	}
	
	/**
	 * @param state
	 */
	public void setState(String state){
		this.state = state;
	}
	
	/**
	 * @param zip
	 */
	public void setZip(int zip){	
		this.zip = zip;
	}
	
	
}
