package com.shwayoe.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class LocationList {
	static String line;
	static String csvSplitBy = ",";
	private static List<Location> locations = new ArrayList<>();
	
	static {
		try (BufferedReader br = new BufferedReader(new FileReader("raw_data\\locations.csv",StandardCharsets.UTF_8))){
			while((line = br.readLine()) != null) {
				String[] data = line.split(csvSplitBy);
				if(!data[0].equals("City")) {
					GeoLocation geolo = new GeoLocation(Integer.parseInt(data[2]), Integer.parseInt(data[3]), Integer.parseInt(data[4]), Integer.parseInt(data[5]));
					Location loc = new Location(data[0],data[1],geolo,Double.parseDouble(data[6]));
					addLocation(loc);
				}
			}
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void addLocation(Location loc) {
		locations.add(loc);
	}
	
	public static List<Location> getLocations(){
		return locations;
	}
	
	public static Location getLocationByCity(String name) {
		for (Location location : locations) {
			if(location.getCity().equals(name)) {
				return location;
			}
		}
		return null;
	}
}
