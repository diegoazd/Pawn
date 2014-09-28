package com.pawn.bean;

public class ListElementBO {
	private AppUser user;
	private Feed feed;
	public ListElementBO() {
	}
	public ListElementBO(AppUser user, Feed feed) {
		this.user=user;
		this.feed=feed;
	}
	public AppUser getUser() {
		return user;
	}
	public void setUser(AppUser user) {
		this.user = user;
	}
	public Feed getFeed() {
		return feed;
	}
	public void setFeed(Feed feed) {
		this.feed = feed;
	}
}
