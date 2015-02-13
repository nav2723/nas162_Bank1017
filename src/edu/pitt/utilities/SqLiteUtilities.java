package edu.pitt.utilities;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

public class SqLiteUtilities implements DbUtilities {

	/* (non-Javadoc)
	 * @see edu.pitt.utilities.DbUtilities#getResultSet(java.lang.String)
	 */
	@Override
	public ResultSet getResultSet(String sql) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.pitt.utilities.DbUtilities#executeQuery(java.lang.String)
	 */
	@Override
	public boolean executeQuery(String sql) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see edu.pitt.utilities.DbUtilities#getDataTable(java.lang.String)
	 */
	@Override
	public DefaultTableModel getDataTable(String sql) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.pitt.utilities.DbUtilities#getDataTable(java.lang.String, java.lang.String[])
	 */
	@Override
	public DefaultTableModel getDataTable(String sqlQuery,
			String[] customColumnNames) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
