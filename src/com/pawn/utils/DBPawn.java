package com.pawn.utils;

import java.util.ArrayList;

import com.pawn.bean.Feed;
import com.pawn.bean.FeedDetail;

public class DBPawn {
	
	private DBConnector dbconn;

	public DBPawn() {
	}

	public DBPawn(DBConnector dbconn) {
		this.dbconn = dbconn;
	}

	public void setDbconn(DBConnector dbconn) {
		this.dbconn = dbconn;
	}

	public int sendFeed(){

		return -1;
	}
	public ArrayList<Feed> getFeed(){
		return null;
	}
	public ArrayList<FeedDetail> getFeedDetail(){
		
		return null;
	}
}
