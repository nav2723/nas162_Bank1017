package edu.pitt.utilities;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

public interface DbUtilities {
	/**
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public ResultSet getResultSet(String sql) throws SQLException;
	
	/**
	 * @param sql
	 * @return
	 */
	public boolean executeQuery(String sql);
	
	/**
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public DefaultTableModel getDataTable(String sql) throws SQLException;
	
	/**
	 * @param sqlQuery
	 * @param customColumnNames
	 * @return
	 * @throws SQLException
	 */
	public DefaultTableModel getDataTable(String sqlQuery, String[] customColumnNames) throws SQLException;
	
}
