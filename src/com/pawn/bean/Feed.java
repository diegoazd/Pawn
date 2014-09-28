package com.pawn.bean;

public class Feed extends FeedDetail {
	private String title;
	private String description;
	private String date;
	private String imgPrincipal;

	public Feed(String title, String description, String date,
			String imgPrincipal, String amountRequired, String[] image) {
		this.title = title;
		this.description = description;
		this.date = date;
		this.imgPrincipal = imgPrincipal;
		super.setAmountRequired(amountRequired);
		super.setImage(image);
	}

	public Feed() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getImgPrincipal() {
		return imgPrincipal;
	}

	public void setImgPrincipal(String imgPrincipal) {
		this.imgPrincipal = imgPrincipal;
	}

}
