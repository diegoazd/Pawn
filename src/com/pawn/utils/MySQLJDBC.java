package com.pawn.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLJDBC implements DBConnector {
	private java.sql.Connection conn;
	private IDBConfig config;

	public static final int autoconnect = 0x01;

	public MySQLJDBC() {

	}

	public MySQLJDBC(IDBConfig config) {
		this.config = config;
	}

	public MySQLJDBC(IDBConfig config, int arg) {
		this.config = config;
		//try {
			switch (arg) {
			case autoconnect:
				try {
					connect();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			default:
				break;
			}
		/*} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}*/
	}

	public void setConfig(IDBConfig config) {
		this.config = config;
	}

	public Connection connect() throws SQLException, ClassNotFoundException {

		if (config != null) {
			Class.forName(config.getDriver());
			conn = DriverManager.getConnection(config.getUrl(),
					config.getUsername(), config.getPassword());
			return conn;
		} else {
			return null;
		}
	}

	public boolean isAlive() {
		try {
			if (conn != null) {
				return !conn.isClosed();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
