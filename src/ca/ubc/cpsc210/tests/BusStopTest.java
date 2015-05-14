package ca.ubc.cpsc210.tests;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import ca.ubc.cpsc210.nextbus.model.BusLocation;
import ca.ubc.cpsc210.nextbus.model.BusRoute;
import ca.ubc.cpsc210.nextbus.model.BusStop;
import ca.ubc.cpsc210.nextbus.model.BusWaitTime;
import ca.ubc.cpsc210.nextbus.util.LatLon;

public class BusStopTest {
	
	BusStop stopA;
	
	@Before
	public void setup() {
		Set<BusRoute> routes = new HashSet<BusRoute>();
		stopA = new BusStop(1, "UBC Bus Loop 6", -123.0, 49.0, routes);
	}
	

	@Test
	public void testGetStopNum() {
		assertTrue(stopA.getStopNum() == 1);
	}
	
	@Test
	public void testGetLocationDesc() {
		assertEquals(stopA.getLocationDesc(), "UBC Bus Loop 6");
	}
	
	@Test
	public void testGetLatLon() {                  // HELP - WHY DOES THIS TEST NOT PASS IF LATLON EQUALS IS OVERRIDDEN?
		assertEquals(stopA.getLatLon(), new LatLon(-123.0, 49.0));
	}
	
	@Test
	public void testGetWaitTimes() {
		assertTrue(stopA.getWaitTimes().size() == 0);
	}
	
	@Test
	public void testGetRouteNamed() {
		assertEquals(stopA.getRouteNamed("route A"), null);
		
		Set<BusRoute> routes = new HashSet<BusRoute>();
		BusRoute routeA = new BusRoute ("routeA");
		BusRoute routeB = new BusRoute ("routeB");
		
		routes.add(routeA);
		routes.add(routeB);
		stopA = new BusStop(1, "UBC Bus Loop 6", -123.0, 49.0, routes);
		assertEquals(stopA.getRouteNamed("routeA"), routeA);
		assertEquals(stopA.getRouteNamed("routeB"), routeB);
		assertEquals(stopA.getRouteNamed("routeC"), null);
	}
	
	@Test
	public void testAddWaitTime() {
		BusRoute routeA = new BusRoute();
		BusWaitTime waitTimeA = new BusWaitTime(routeA, 0, false);
		assertTrue(stopA.getWaitTimes().size() == 0);
		// assertEquals(stopA.getWaitTimes(), null); HELP - RETURNING ERROR
		stopA.addWaitTime(waitTimeA);
		assertTrue(stopA.getWaitTimes().size() == 1);
		// assertEquals(stopA.getWaitTimes(), null); HELP - RETURNING ERROR
		// FINISH - GET the waittime??
	}
	
	@Test
	public void testClearWaitTimes() {
		BusRoute routeA = new BusRoute();
		BusWaitTime waitTimeA = new BusWaitTime(routeA, 0, false);
		assertTrue(stopA.getWaitTimes().size() == 0);
		stopA.addWaitTime(waitTimeA);
		assertTrue(stopA.getWaitTimes().size() == 1);
		stopA.clearWaitTimes();
		assertTrue(stopA.getWaitTimes().size() == 0);
	}
	
	@Test
	public void testGetBusLocation() { 
		assertTrue(stopA.getBusLocations().size() == 0);
		
	}
	
	@Test
	public void testAddBusLocation() {
		assertTrue(stopA.getBusLocations().size() == 0);
		
		BusRoute routeA = new BusRoute("routeA");
		BusRoute routeB = new BusRoute("routeB");
		BusLocation locationA = new BusLocation(routeA, -123.0, 49.0, "UBC", "3:00:00");
		BusLocation locationB = new BusLocation(routeB, -123.0, 49.0, "UBC", "3:00:00");
		stopA.addBusLocation(locationA);   
		assertTrue(stopA.getBusLocations().size() == 1);
		
		stopA.addBusLocation(locationB); 
		assertTrue(stopA.getBusLocations().size() == 2);
		assertEquals(stopA.getBusLocations().get(0), locationA);
		assertEquals(stopA.getBusLocations().get(1), locationB);
	}
	
	@Test
	public void testClearBusLocations() {
		assertTrue(stopA.getBusLocations().size() == 0);
		
		BusRoute routeA = new BusRoute("routeA");
		BusRoute routeB = new BusRoute("routeB");
		BusLocation locationA = new BusLocation(routeA, -123.0, 49.0, "UBC", "3:00:00");
		BusLocation locationB = new BusLocation(routeB, -123.0, 49.0, "UBC", "3:00:00");
		stopA.addBusLocation(locationA);   
		assertTrue(stopA.getBusLocations().size() == 1);
		
		stopA.addBusLocation(locationB); 
		assertTrue(stopA.getBusLocations().size() == 2);
		assertEquals(stopA.getBusLocations().get(0), locationA);
		assertEquals(stopA.getBusLocations().get(1), locationB);
		
		stopA.clearBusLocations();
		assertTrue(stopA.getBusLocations().size() == 0);
	}
	
	@Test
	public void testToString() {
		assertEquals(stopA.toString(), "1 UBC Bus Loop 6");
	}
	
	
	
}
