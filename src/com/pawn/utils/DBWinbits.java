package com.pawn.utils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;



public class DBWinbits {
	private DBConnector dbconn;

	private static final String SPGetDailySalesByArea = "{call getDailySalesByArea(?) }";
	
	public DBWinbits() {
	}

	public DBWinbits(DBConnector connector) {
		this.dbconn = connector;
	}

	public void setConnector(DBConnector connector) {
		this.dbconn = connector;
	}

	// 2014-02-11
	public void getDailySalesByArea(String input) {
		Connection conn = null;
		try {
			if (!dbconn.isAlive()) {
				conn = dbconn.connect();
			}
			if (conn != null) {
				CallableStatement cs = conn.prepareCall(SPGetDailySalesByArea);
				cs.setString(1, input);
				cs.execute();

				ResultSet rs = cs.getResultSet();
				while (rs.next()) {
					System.out.println("id : " + rs.getString("id"));
					System.out.println("cash_total : "
							+ rs.getString("cash_total"));
					System.out.println("items_total : "
							+ rs.getString("items_total"));
				}
				rs.close();
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
}
