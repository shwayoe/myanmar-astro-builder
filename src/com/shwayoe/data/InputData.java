package com.shwayoe.data;

public class InputData {
	private String name;
	private Location loc;
	private int dy;
	private int mn;
	private int yr;
	private int hh;
	private int mm;
	private int ss;
	
	public InputData(int dy,int mn,int yr,int hh, int mm, int ss) {
		this.dy = dy;
		this.mn = mn;
		this.yr = yr;
		this.hh = hh;
		this.mm = mm;
		this.ss = ss;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDay() {
		return dy;
	}

	public int getMonth() {
		return mn;
	}

	public int getYear() {
		return yr;
	}

	public int getHours() {
		return hh;
	}

	public int getMinutes() {
		return mm;
	}

	public int getSeconds() {
		return ss;
	}

	public Location getLocation() {
		return loc;
	}

	public void setLocation(Location loc) {
		this.loc = loc;
	}
}
