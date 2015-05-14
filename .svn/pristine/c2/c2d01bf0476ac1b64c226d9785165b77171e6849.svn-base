package ca.ubc.cpsc210.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ca.ubc.cpsc210.nextbus.model.BusRoute;
import ca.ubc.cpsc210.nextbus.util.LatLon;
import ca.ubc.cpsc210.nextbus.util.Segment;

public class BusRouteTests {
	
	private BusRoute routeA;
	
	@Before
	public void setUp(){
		routeA = new BusRoute("Route A");	
	}

	@Test
	public void testGetName() {
		assertEquals(routeA.getName(), "Route A");	
	}
	
	@Test
	public void testSetName() {
		routeA.setName("Route B");
		assertEquals(routeA.getName(), "Route B");	
		assertFalse(routeA.getName() == "Route A");
	}
	
	@Test
	public void testGetBounds() {
		assertTrue(routeA.getBounds().getNorth() == 90.0);
		assertTrue(routeA.getBounds().getSouth() == -90.0);
		assertTrue(routeA.getBounds().getEast() == 180.0);
		assertTrue(routeA.getBounds().getWest() == -180.0);
	}
	
	@Test
	public void testSetBounds() {
		routeA.setBounds(60.0, -60.0, 130.0, -130.0);
		assertTrue(routeA.getBounds().getNorth() == 60.0);
		assertTrue(routeA.getBounds().getSouth() == -60.0);
		assertTrue(routeA.getBounds().getEast() == 130.0);
		assertTrue(routeA.getBounds().getWest() == -130.0);
	}
	
	
	
	@Test
	public void testAddSegment() {
		assertFalse(routeA.hasSegments()); // empty list of segments
		// segment with a point
		Segment seg1 = new Segment();
		assertTrue(seg1.getPoints().size() == 0);
		seg1.addPoint(new LatLon(10.0, 60.0));
		assertTrue(seg1.getPoints().size() == 1);
		// another segment with a point
		Segment seg2 = new Segment();
		assertTrue(seg2.getPoints().size() == 0);
		seg2.addPoint(new LatLon(20.0, -50.0)); 
		assertTrue(seg2.getPoints().size() == 1);
		routeA.addSegment(seg1);
		assertTrue(routeA.getSegments().size() == 1);
		routeA.addSegment(seg2);
		assertTrue(routeA.getSegments().size() == 2);
		assertTrue(routeA.hasSegments());
	}
	
	@Test
	public void testToString() {
		assertEquals(routeA.toString(), "Route A");
	}
	
	@Test
	public void testGetUrl() {
		assertEquals(routeA.getMapURL(), null);
	}
	
	@Test
	public void testSetUrl() {
		assertEquals(routeA.getMapURL(), null);
		routeA.setMapURL("URL");
		assertEquals(routeA.getMapURL(), "URL");
	}
	
}

