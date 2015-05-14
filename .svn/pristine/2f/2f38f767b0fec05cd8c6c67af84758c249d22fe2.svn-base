package ca.ubc.cpsc210.nextbus.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import ca.ubc.cpsc210.nextbus.util.LatLon;


/**
 * Bus stop information including stop number, description, location (lat/lon),
 * bus routes serving this stop, estimated wait times for buses serving this stop
 * and bus locations for buses serving this stop.
 */
public class BusStop {
	
	private LatLon latlon;
	private List<BusLocation> busLocations; // HELP - APPARENT TYPE LIST?
	private Set<BusRoute> routes; // HELP - APPARENT TYPE SET?
	private Set<BusWaitTime> waitTimes; // HELP - APPARENT TYPE SET?
	private int stopNum;
	private java.lang.String locationDescription;
	
	
	public BusStop(int stopNum, java.lang.String location, double lat, double lon, 
			java.util.Set<BusRoute> routes) { // HELP - THROW AN EXCEPTION FOR A ROUTES PARAMETER THAT IS NOT A SET OF BUSROUTES
		latlon = new LatLon(lat, lon);
		busLocations = new ArrayList<BusLocation>(); // HELP - ACTUAL TYPE LINKEDLIST?
		this.routes = routes; // HELP - CORRECT ASSIGNMENT?
		waitTimes = new HashSet<BusWaitTime>(); // HELP - ACTUAL TYPE HASHSET?
		this.stopNum = stopNum;
		locationDescription = location;
	}
	
	public int getStopNum(){
		return stopNum;
	}
	
	public java.lang.String getLocationDesc(){
		return locationDescription;
	}
	
	public LatLon getLatLon() {
		return latlon;
	}
	
	public java.util.Set<BusWaitTime> getWaitTimes() {
		return waitTimes;
	}
	
	public BusRoute getRouteNamed(java.lang.String routeName) { // HELP - USE HASHSET SEARCH OR LIST SEARCH?
		for (BusRoute r : routes) {
		if (r.toString().equals(routeName))
			return r;
		}
		return null; 	
		}
		
	
	public void addWaitTime(BusWaitTime bwt) {
		waitTimes.add(bwt);
	}
	
	public void clearWaitTimes() {
		waitTimes.clear();
	}
	
	public void addBusLocation(BusLocation bl) {
		busLocations.add(bl);
	}
	
	public void clearBusLocations() {
		busLocations.clear();
	}
	
	public java.util.List<BusLocation> getBusLocations() {
		return busLocations;
	}
	
	@Override
	public java.lang.String toString() {
		return Integer.toString(stopNum) + " " + locationDescription;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + stopNum;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BusStop other = (BusStop) obj;
		if (stopNum != other.stopNum)
			return false;
		return true;
	}
	
	
	
	
}
