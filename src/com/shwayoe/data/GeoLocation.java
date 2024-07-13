package com.shwayoe.data;

public class GeoLocation {
	private int latd;
	private int latm;
	private int logd;
	private int logm;
	
	public GeoLocation(int latd,int latm,int logd,int logm) {
		this.latd = latd;
		this.latm = latm;
		this.logd = logd;
		this.logm = logm;
	}
	
	public int getLatitude() {
		return latd;
	}
	
	public int getLatMinutes() {
		return latm;
	}
	
	public int getLogitude() {
		return logd;
	}
	
	public int getLogMinutes() {
		return logm;
	}
	
}
