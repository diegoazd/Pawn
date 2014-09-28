package com.pawn.dao;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;

import com.pawn.utils.DBConnector;

public class UserDAO {
	private DBConnector dbconn;
	
	public UserDAO() {
	}

	public UserDAO(DBConnector connector) {
		this.dbconn = connector;
	}

	public void setConnector(DBConnector connector) {
		this.dbconn = connector;
	}
	
	public String registerUser(String email, String password) {
		Connection conn = null;
		String apiToken ="";
		try {
			if (!dbconn.isAlive()) {
				conn = dbconn.connect();
			}

			if (conn != null) {
				Statement statement = conn.createStatement();
				apiToken = generateApiToken(email+password);
				String query = "INSERT INTO User (email,password,apiToken) values('"+email+"','"+password+"','"+apiToken+"')";
				statement.execute(query);
				
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			apiToken ="";
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			apiToken ="";

		}
		return apiToken;
	}
	
	private String generateApiToken(String encoding){
		MessageDigest sha256;
		StringBuffer sb=new StringBuffer();
		try {
			sha256 = MessageDigest.getInstance("SHA-256");
			sha256.update(encoding.getBytes("UTF-8"));
			byte[] digest = sha256.digest();
			
			for(int i=0;i<digest.length;i++){
			    sb.append(String.format("%02x", digest[i]));
			}
		} catch (NoSuchAlgorithmException e) {			
			e.printStackTrace();
			sb.append("fallo=(");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return sb.toString(); 
		
	}
}
