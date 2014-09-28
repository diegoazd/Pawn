package com.pawn.utils;


public class MySQLConfig implements IDBConfig{
  
	private String driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost/pawn";
    
    private String username = "root";
    private String password = "";


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
