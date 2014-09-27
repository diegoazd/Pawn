package com.winbits.report.db;

public class SQLServerConfig implements IDBConfig{
	// "com.microsoft.sqlserver.jdbc.SQLServerDriver"
	// String connectionUrl = "jdbc:sqlserver://ServerName\\sqlexpress;" +
	// "database=DBName;" +
	// "user=UserName;" +
	// "password=Password";
	
    private String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private String url ="jdbc:sqlserver://54.200.35.28:1433;" +
    "databaseName=vertical;"
    + "user=sqluserdream;password=dr3@amsql$";
    
    private String username = "sqluserdream";
    private String password = "dr3@amsql$";
	
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
