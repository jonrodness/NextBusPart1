package ca.ubc.cpsc210.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import ca.ubc.cpsc210.exception.TranslinkException;
import ca.ubc.cpsc210.nextbus.model.BusLocation;
import ca.ubc.cpsc210.nextbus.model.BusRoute;
import ca.ubc.cpsc210.nextbus.model.BusStop;
import ca.ubc.cpsc210.nextbus.model.BusWaitTime;
import ca.ubc.cpsc210.nextbus.translink.ITranslinkService;
import ca.ubc.cpsc210.nextbus.translink.TranslinkStub;
import ca.ubc.cpsc210.nextbus.util.BoundingBox;
import ca.ubc.cpsc210.nextbus.util.LatLon;
import ca.ubc.cpsc210.nextbus.util.Segment;

public class TranslinkTests {
    public static final int BUS_STOP = 0;
    public static final int STOP_ESTIMATES = 1;
    public static final int BUS_LOCATIONS = 2;
    public static final int BUS_ROUTE = 3;
    private static final int NUM_FILES = 4; 
    private static final double DELTA = 1.0e-9;
    
    private ITranslinkService translink;
    
    @Before
    public void setUp() throws Exception {
        List<File> dataFiles = new ArrayList<File>(NUM_FILES);
        initFiles(dataFiles);
        translink = new TranslinkStub(dataFiles);
    }
    
    private void initFiles(List<File> dataFiles) {
        dataFiles.add(BUS_STOP, new File("TestFiles/Student/busStop50319.xml"));
        dataFiles.add(STOP_ESTIMATES, new File("TestFiles/Student/busStopEstimates50319.xml"));
        dataFiles.add(BUS_LOCATIONS, new File("TestFiles/Student/busLocationsForStop50319.xml"));
        dataFiles.add(BUS_ROUTE, new File("TestFiles/Student/busRoute009.kmz"));
    }
    
    @Test
    public void testGetBusStopFromTranslink() throws TranslinkException {
        BusStop stop = translink.getBusStop("50319");
        assertEquals(50319, stop.getStopNum());
        assertEquals(new LatLon(49.263990, -123.167350), stop.getLatLon());
        assertEquals("EB W BROADWAY FS MACDONALD ST", stop.getLocationDesc());
        assertEquals(null, stop.getRouteNamed("044"));
        BusRoute route = stop.getRouteNamed("009");
        assertEquals("009", route.getName());
        assertEquals(null, route.getMapURL());
        assertFalse(route.hasSegments());
        route = stop.getRouteNamed("N17");
        assertEquals("N17", route.getName());
        assertEquals(null, route.getMapURL());
        assertFalse(route.hasSegments());
        route = stop.getRouteNamed("014");
        assertEquals("014", route.getName());
        assertEquals(null, route.getMapURL());
        assertFalse(route.hasSegments());
        route = stop.getRouteNamed("099");
        assertEquals("099", route.getName());
        assertEquals(null, route.getMapURL());
        assertFalse(route.hasSegments());
        
    }
    
    @Test
    public void testAddBusLocationsForStop() throws TranslinkException {
    	BusStop stop = translink.getBusStop("50319");
    	translink.addBusLocationsForStop(stop);
    	assertEquals(50319, stop.getStopNum());
    	List<BusLocation> busLocations = stop.getBusLocations();
    	assertEquals(busLocations.size(), 21);
    	BusLocation location0 = busLocations.get(0);
    	assertEquals(location0.getRoute(), new BusRoute("014"));
    	assertEquals(location0.getLatLon(), new LatLon(49.265933, -123.248333));
    	assertEquals(location0.getDestination(), "HASTINGS");
    	assertFalse(location0.getDestination() == "test");
    	assertEquals(location0.getTime(), "09:27:53 am");
    	BusLocation location10 = busLocations.get(10);
    	assertEquals(location10.getRoute(), new BusRoute("014"));
    	assertEquals(location10.getLatLon(), new LatLon(49.281333, -123.098750));
    	assertEquals(location10.getDestination(), "HASTINGS");
    	assertEquals(location10.getTime(), "09:36:45 am");
    	BusLocation location20 = busLocations.get(20);
    	assertEquals(location20.getRoute(), new BusRoute("099"));
    	assertEquals(location20.getLatLon(), new LatLon(49.263300, -123.119917));
    	assertEquals(location20.getDestination(), "COMM'L-BDWAY STN");
    	assertEquals(location20.getTime(), "09:37:45 am");
    }
    
    @Test
    public void testAddWaitTimeEstimatesToStop() throws TranslinkException {
    	BusStop stop = translink.getBusStop("50319");
    	translink.addWaitTimeEstimatesToStop(stop);
    	Set<BusWaitTime> waitTimes = stop.getWaitTimes();
    	assertEquals(waitTimes.size(), 9);
    	assertTrue(waitTimes.contains(new BusWaitTime(new BusRoute("009"), 11, false)));
    	assertTrue(waitTimes.contains(new BusWaitTime(new BusRoute("009"), 22, false)));
    	assertTrue(waitTimes.contains(new BusWaitTime(new BusRoute("009"), 34, false)));
    	assertFalse(waitTimes.contains(new BusWaitTime(new BusRoute("009"), 87, false)));
    	// this was never added
    	assertTrue(waitTimes.contains(new BusWaitTime(new BusRoute("014"), 6, false)));
    	assertTrue(waitTimes.contains(new BusWaitTime(new BusRoute("014"), 16, false)));
    	assertTrue(waitTimes.contains(new BusWaitTime(new BusRoute("014"), 20, false)));
    	assertTrue(waitTimes.contains(new BusWaitTime(new BusRoute("099"), 3, false)));
    	assertTrue(waitTimes.contains(new BusWaitTime(new BusRoute("099"), 10, false)));
    	assertTrue(waitTimes.contains(new BusWaitTime(new BusRoute("099"), 15, false)));
    }
    

    

}
