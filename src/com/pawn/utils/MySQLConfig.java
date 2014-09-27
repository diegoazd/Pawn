package com.winbits.report.db;

public class MySQLConfig implements IDBConfig{
   /* private String driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://72.55.168.154/winbits_staging";
    
    private String username = "winbitsdev";
    private String password = "n6qD6bLF";
    */
	private String driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost/winbits_staging2";
    
    private String username = "root";
    private String password = "";
    /*
     * 
     * Winbits (MySQL)
Host: 72.55.168.154
User: winbitsdev
Password: n6qD6bLF
     * */
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
