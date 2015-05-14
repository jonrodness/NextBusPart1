package ca.ubc.cpsc210.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.ubc.cpsc210.nextbus.model.BusRoute;
import ca.ubc.cpsc210.nextbus.model.BusWaitTime;

public class BusWaitTimeTests {
	
	BusRoute routeA = new BusRoute();
	BusWaitTime waitTime;
	
	
	@Before
	public void setup() {
		waitTime = new BusWaitTime(routeA, 0, false);
	}
	
	@Test
	public void testConstructor() {
		assertEquals(waitTime.getRoute().getName(), "unknown");
	}

	@Test
	public void testGetRoute() {
		assertTrue(waitTime.getRoute() == routeA);
	}
	
	@Test
	public void testGetEstimate() {
		assertTrue(waitTime.getEstimate() == 0);
	}
	
	@Test
	public void testIsCancelled() {
		assertFalse(waitTime.isCancelled());
	}
	
	@Test
	public void testToString() {
		assertEquals(waitTime.toString(), "unknown: NOW");
		waitTime = new BusWaitTime(routeA, 2, false);
		assertEquals(waitTime.toString(), "unknown: 2 mins");
		waitTime = new BusWaitTime(routeA, 0, true);
		assertEquals(waitTime.toString(), "unknown: NOW - cancelled");
		waitTime = new BusWaitTime(routeA, 2, true);
		assertEquals(waitTime.toString(), "unknown: 2 mins - cancelled");
		
	}
	
// HELP - TESTS FOR COMPARETO AND EQUALS AND HASH METHODS?
}
