package com.pawn.bean;

public class AppUser {
	private String avatar;
	private String username;
	private String email;
	private String servicio;

	

	public AppUser(String avatar, String username, String email) {
		this.avatar = avatar;
		this.username = username;
		this.email = email;
	}
	
	public AppUser(String avatar, String username, String email, String servicio) {
		this.avatar = avatar;
		this.username = username;
		this.email = email;
		this.servicio = servicio;
	}

	public AppUser() {
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}
	public String getServicio() {
		return servicio;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
}
