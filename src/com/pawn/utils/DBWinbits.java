package com.winbits.report.db;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ParameterMetaData;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.winbits.report.bo.CampaignBO;
import com.winbits.report.bo.CampaignTransaction;
import com.winbits.report.bo.DetailedSaleBO;
import com.winbits.report.bo.HistoricTransactionBO;

public class DBWinbits {
	private DBConnector dbconn;

	private static final String SPGetDailySalesByArea = "{call getDailySalesByArea(?) }";
	private static final String SPGetCampaignTransactionsByDay = "{call getCampaignTransactionsByDay(?) }";
	private static final String SPGetHistoricSalesBySku = "{call getHistoricSalesBySku(?) }";
	private static final String SPGetSalesBySku = "{call getSalesBySku(?,?) }";
	private static final String SPGetSkuSalesByDay = "{call getSkuSalesByDay(?,?,?) }";
	
	//call getSkuSalesByDay('2013-12-05',792,20)
//call getSalesBySku(792,11);
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

	public ArrayList<CampaignTransaction> getCampaignTransactionsByDay(String date) {
		Connection conn = null;
		ArrayList<CampaignTransaction> cTransaction=null;
		
		try {
			if(!dbconn.isAlive()){
				conn = dbconn.connect();	
			}
			if (conn != null) {
				cTransaction= new ArrayList<CampaignTransaction>();
				CallableStatement cs = conn.prepareCall(SPGetCampaignTransactionsByDay);
				cs.setString(1, date);
				cs.execute();

				ResultSet rs = cs.getResultSet();
				while (rs.next()) {
					cTransaction.add(new CampaignTransaction(Integer.parseInt(rs.getString("sku_id")),
							Integer.parseInt(rs.getString("campaign_id")), 
							Integer.parseInt(rs.getString("order_detail_id")),rs.getString("description"),rs.getString("categoryName")));
				}
				rs.close();
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return cTransaction;
	}

	public ArrayList<HistoricTransactionBO>  getHistoricSales(ArrayList<CampaignTransaction>sku){
		Connection conn = null;
		ArrayList<HistoricTransactionBO> historic=null;
		try {
			
			if(!dbconn.isAlive()){
				conn = dbconn.connect();	
			}
			
			if (conn != null) {
				Integer integer=null;
				CallableStatement cs;
				ResultSet rs;
				historic= new ArrayList<HistoricTransactionBO>();
				for (int i = 0; i < sku.size(); i++) {
					integer=sku.get(i).getIdSku();
					cs = conn.prepareCall(SPGetHistoricSalesBySku);
					cs.setInt(1, integer.intValue());
					cs.execute();

					rs = cs.getResultSet();
					while (rs.next()) {
						//category.add(new CategoryBO(Integer.parseInt(rs.getString("idCampaign")),Integer.parseInt(rs.getString("idCategoryWinbits")),rs.getString("categoryName")));
					//
						System.out.println("id : " + rs.getString("id"));
						System.out.println("id : " + rs.getString("detail_id"));
						System.out.println("id : " + rs.getString("sku"));
						System.out.println("id : " + rs.getString("sku_profile_id"));
						System.out.println("id : " + rs.getString("date_created"));
						System.out.println("id : " + rs.getString("quantity"));
						System.out.println("id : " + rs.getString("currentSale"));
						System.out.println("id : " + rs.getString("currentVirtualSale"));
						System.out.println("id : " + rs.getString("virtualCost"));
						System.out.println("id : " + rs.getString("trackingInventory"));
						System.out.println("id : " + rs.getString("invAvailable"));
						System.out.println("id : " + rs.getString("id_hdetail"));
						System.out.println("id : " + rs.getString("hdate_created"));
						System.out.println("id : " + rs.getString("cost"));
						System.out.println("id : " + rs.getString("quantity_received"));
						historic.add(new HistoricTransactionBO(
								Integer.parseInt(rs.getString("id")),
								Integer.parseInt(rs.getString("detail_id")),
								Integer.parseInt(rs.getString("sku")),
								Integer.parseInt(rs.getString("sku_profile_id")),
								rs.getString("date_created"),
								Integer.parseInt(rs.getString("quantity")),
								new BigDecimal(rs.getString("currentSale")),
								new BigDecimal(rs.getString("currentVirtualSale")),
								new BigDecimal(rs.getString("virtualCost")),
								Integer.parseInt(rs.getString("trackingInventory")),
								Integer.parseInt(rs.getString("invAvailable")),
								rs.getString("hdate_created"),
								new BigDecimal(rs.getString("cost")),
								Integer.parseInt(rs.getString("quantity_received"))
								));
					}
					rs.close();
				}
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}	
		return historic;
	}
	public ArrayList<CampaignBO>  getSalesBySku(ArrayList<CampaignBO> cBO,ArrayList<CampaignTransaction> t){
		Connection conn = null;
		try {
			
			if(!dbconn.isAlive()){
				conn = dbconn.connect();	
			}
			
			if (conn != null) {
				Integer idCampaign=null;
				Integer idSku=null;
				
				CallableStatement cs;
				ResultSet rs;
				int tSize=t.size();
				for (int i = 0; i < tSize; i++) {
					CampaignTransaction transaction=t.get(i);
					idCampaign=t.get(i).getIdCampaign();
					idSku=t.get(i).getIdSku();
					cs = conn.prepareCall(SPGetSalesBySku);
					cs.setInt(1, idSku.intValue());
					cs.setInt(2, idCampaign.intValue());
					
					cs.execute();

					rs = cs.getResultSet();
					while (rs.next()) {
						//category.add(new CategoryBO(Integer.parseInt(rs.getString("idCampaign")),Integer.parseInt(rs.getString("idCategoryWinbits")),rs.getString("categoryName")));
					//
						//System.out.println("id : " + rs.getString("id"));
						System.out.println("id : " + rs.getString("detail_id"));
						System.out.println("id : " + rs.getString("sku"));
						System.out.println("id : " + rs.getString("sku_profile_id"));
						System.out.println("id : " + rs.getString("date_created"));
						System.out.println("id : " + rs.getString("quantity"));
						System.out.println("id : " + rs.getString("currentSale"));
						System.out.println("id : " + rs.getString("currentVirtualSale"));
						System.out.println("id : " + rs.getString("virtualCost"));
						System.out.println("id : " + rs.getString("trackingInventory"));
						System.out.println("id : " + rs.getString("invAvailable"));
						//System.out.println("id : " + rs.getString("id_hdetail"));
						//System.out.println("id : " + rs.getString("hdate_created"));
						System.out.println("id : " + rs.getString("cost"));
						
						System.out.println("Humn!"+transaction.getIdCampaign()+" "+transaction.getIdOrderDetail()+" "+Integer.parseInt(rs.getString("detail_id"))
								+" "+transaction.getIdSku()+" "+Integer.parseInt(rs.getString("sku")));
						if(transaction.getIdCampaign()==Integer.parseInt(rs.getString("detail_id"))
								&&transaction.getIdSku()==Integer.parseInt(rs.getString("sku"))){
							CampaignBO bo= cBO.get(transaction.getIdReference());
							DetailedSaleBO sale= 
									new DetailedSaleBO(
											new BigDecimal(rs.getString("currentSale")),
											bo.getName(),
											new Float(rs.getString("currentSale")),
											new BigDecimal(rs.getString("currentSale")), 0,
											Integer.parseInt(rs.getString("quantity")), rs.getString("id"),"",
											"", "", bo.getName(),
											bo.getStartDate(),
											bo.getEndDate());
							bo.addSummary(sale);
							cBO.set(transaction.getIdReference(), bo);
							System.out.println("THIS SHIT WORKS!");
							//cBO.get(transaction.getIdReference()).addSummary(new DetailedSaleBO(ht.get getht.getSku()));
							/*DetailedSaleBO sale= new DetailedSaleBO(ammount, concept, shareSale, profitMargin, shareMargin, itemsSold, id, visibility, category, subcategory, campaign, startCampaign, endCampaign)
							
							cBO.get(transaction.getIdReference()).addSummary(sale);
						*/
						
						}
						//jajaSystem.out.println("id : " + rs.getString("quantity_received"));
						/*historic.add(new HistoricTransactionBO(
								Integer.parseInt(rs.getString("id")),
								Integer.parseInt(rs.getString("detail_id")),
								Integer.parseInt(rs.getString("sku")),
								Integer.parseInt(rs.getString("sku_profile_id")),
								rs.getString("date_created"),
								Integer.parseInt(rs.getString("quantity")),
								new BigDecimal(rs.getString("currentSale")),
								new BigDecimal(rs.getString("currentVirtualSale")),
								new BigDecimal(rs.getString("virtualCost")),
								Integer.parseInt(rs.getString("trackingInventory")),
								Integer.parseInt(rs.getString("invAvailable")),
								rs.getString("hdate_created"),
								new BigDecimal(rs.getString("cost")),
								Integer.parseInt(rs.getString("quantity_received"))
								));*/
					}
					rs.close();
				}
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}	
		return cBO;
	}



public ArrayList<CampaignBO>  getSalesBySku(ArrayList<CampaignBO> cBO,ArrayList<CampaignTransaction> t, String date){
	Connection conn = null;
	try {
		
		if(!dbconn.isAlive()){
			conn = dbconn.connect();	
		}
		
		if (conn != null) {
			Integer idCampaign=null;
			Integer idSku=null;
			
			CallableStatement cs;
			ResultSet rs;
			int tSize=t.size();
			for (int i = 0; i < tSize; i++) {
				CampaignTransaction transaction=t.get(i);
				idCampaign=t.get(i).getIdCampaign();
				idSku=t.get(i).getIdSku();
				cs = conn.prepareCall(SPGetSkuSalesByDay);
				cs.setString(1, date);
				cs.setInt(2, idSku.intValue());
				cs.setInt(3, idCampaign.intValue());
				
				cs.execute();

				rs = cs.getResultSet();
				while (rs.next()) {
					//category.add(new CategoryBO(Integer.parseInt(rs.getString("idCampaign")),Integer.parseInt(rs.getString("idCategoryWinbits")),rs.getString("categoryName")));
				//
					System.out.println(rs.getString("id"));
					System.out.println(rs.getString("amount"));
					System.out.println(rs.getString("date_created"));
					System.out.println(rs.getString("quantity"));
					System.out.println(rs.getString("price"));
					System.out.println(rs.getString("quantity_on_hand"));
					System.out.println(rs.getString("sku_id"));
					System.out.println(rs.getString("vertical_id"));
					System.out.println(rs.getString("expected_sold"));
					System.out.println(rs.getString("virtual_cost"));
					System.out.println(rs.getString("real_cost"));
					System.out.println(rs.getString("campaign_id"));
					
					if(rs.getString("id")==null){
						continue;
					}
					
					System.out.println("Humn!"+transaction.getIdCampaign()+" "+transaction.getIdOrderDetail()+" "+Integer.parseInt(rs.getString("id"))
							+" "+transaction.getIdSku()+" "+Integer.parseInt(rs.getString("sku_id")));
					if(transaction.getIdCampaign()==Integer.parseInt(rs.getString("campaign_id"))
							&&transaction.getIdSku()==Integer.parseInt(rs.getString("sku_id"))){
						CampaignBO bo= cBO.get(transaction.getIdReference());
						cBO.get(transaction.getIdReference()).addSummary(new DetailedSaleBO(
								new BigDecimal(rs.getString("amount")),
								bo.getName(),
								new Float(rs.getString("amount")),
								new BigDecimal(rs.getString("amount")), 0,
								Integer.parseInt(rs.getString("quantity")), rs.getString("id"),"",transaction.getName(),
								(transaction.getDescription()==null)?  "-" : transaction.getDescription().split(">")[0], bo.getName(),
								bo.getStartDate(),
								bo.getEndDate()));
			
						System.out.println("THIS SHIT WORKS!");	
					}
				}
				rs.close();
			}
			conn.close();
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}	
	return cBO;
}

}
