package com.keviiweb.keips;

public class BonusCCA {
	
	private int pts;
	private String description;
	
	public BonusCCA(int pts, String description) {
		this.pts = pts;
		this.description = description;
	}
	
	public int getPts() {
		return this.pts;
	}
	
	public String getDescription() {
		return this.description;
	}
}
