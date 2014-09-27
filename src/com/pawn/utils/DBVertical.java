package com.winbits.report.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.winbits.report.bo.CampaignBO;
import com.winbits.report.bo.CampaignTransaction;
import com.winbits.report.bo.CategoryBO;

public class DBVertical {
	private DBConnector dbconn;

	private static final String getCampaignsByDateAndVertical = "{call getCampaignsByDateAndVertical(?,?) }";
	private static final String getCategoriesByDateAndVertical = "{call getCategoriesByDateAndVertical(?,?) }";
	
	
	public DBVertical() {
	}

	public DBVertical(DBConnector connector) {
		this.dbconn = connector;
	}

	public void setConnector(DBConnector connector) {
		this.dbconn = connector;
	}

	// 2014-02-11
	public void getDailySalesByArea(String input, int idVertical) {
		Connection conn = null;
		try {
			if(!dbconn.isAlive()){
				conn = dbconn.connect();	
			}
			
			if (conn != null) {
				CallableStatement cs = conn.prepareCall(getCategoriesByDateAndVertical);
				cs.setString(1, input);
				cs.setInt(2, idVertical);
				cs.execute();

				ResultSet rs = cs.getResultSet();
				while (rs.next()) {
					System.out.println("id : " + rs.getString("id"));
					System.out.println("idVertical : " + rs.getString("idVertical"));
					System.out.println("campaignName : " + rs.getString("campaignName"));
					System.out.println("startDate : " + rs.getString("startDate"));
					System.out.println("endDate : " + rs.getString("endDate"));
					
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
	public ArrayList<CampaignBO> getCampaignVertical(String date, int idVertical){
		Connection conn = null;
		ArrayList<CampaignBO> campaign=null;
		try {
			
			if(!dbconn.isAlive()){
				conn = dbconn.connect();	
			}
			
			if (conn != null) {
				campaign= new ArrayList<CampaignBO>();
				CallableStatement cs = conn.prepareCall(getCampaignsByDateAndVertical);
				cs.setString(1, date);
				cs.setInt(2, idVertical);
				cs.execute();

				ResultSet rs = cs.getResultSet();
				while (rs.next()) {
					campaign.add(new CampaignBO(Integer.parseInt(rs.getString("id")),Integer.parseInt( rs.getString("idVertical")),rs.getString("campaignName"),rs.getString("startDate"),rs.getString("endDate")));
				}
				rs.close();
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}	
		return campaign;
	}
	
	public ArrayList<CategoryBO> getCategoriesVertical(String date, int idVertical){
		Connection conn = null;
		ArrayList<CategoryBO> category=null;
		try {
			
			if(!dbconn.isAlive()){
				conn = dbconn.connect();	
			}
			
			if (conn != null) {
				category= new ArrayList<CategoryBO>();
				CallableStatement cs = conn.prepareCall(getCategoriesByDateAndVertical);
				cs.setString(1, date);
				cs.setInt(2, idVertical);
				cs.execute();

				ResultSet rs = cs.getResultSet();
				while (rs.next()) {
					category.add(new CategoryBO(Integer.parseInt(rs.getString("idCampaign")),Integer.parseInt(rs.getString("idCategoryWinbits")),rs.getString("categoryName")));
				}
				rs.close();
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}	
		return category;
	}
	
}
