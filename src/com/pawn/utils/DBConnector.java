package com.winbits.report.db;

import java.sql.Connection;
import java.sql.SQLException;

public interface DBConnector {
	public Connection connect() throws SQLException, ClassNotFoundException;

	public boolean isAlive();

	public void setConfig(IDBConfig config);
}
