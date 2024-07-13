package com.shwayoe.data;

public class Location {
	private String city;
	private String country;
	private GeoLocation geolocations;
	private double timezone;
	
	public Location(String city,String country,GeoLocation geoloc,double timezone) {
		this.city = city;
		this.country = country;
		this.geolocations = geoloc;
		this.timezone = timezone;
	}
	
	public String getCity() {
		return city;
	}
	
	public String getCountry() {
		return country;
	}
	
	public GeoLocation getGeoLocation() {
		return geolocations;
	}
	
	public double getTimeZone() {
		return timezone;
	}
}
