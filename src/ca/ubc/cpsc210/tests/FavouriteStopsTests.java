package ca.ubc.cpsc210.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.ubc.cpsc210.nextbus.model.BusStop;
import ca.ubc.cpsc210.nextbus.model.FavouriteStops;

public class FavouriteStopsTests {
	private FavouriteStops favourites;
	private BusStop stop1;
	private BusStop stop2;
	private BusStop stop3;
	private BusStop stop4;
	private List<BusStop> stops;
       
    @Before
    public void setUp() throws Exception {
        favourites = FavouriteStops.getInstance();
        favourites.clear();
        
        stop1 = new BusStop(1, "", 0, 0, null);
        stop2 = new BusStop(2, "", 0, 0, null);
        stop3 = new BusStop(3, "", 0, 0, null);
        stop4 = new BusStop(4, "", 0, 0, null);
        stops = new ArrayList<BusStop>();
        stops.add(stop1);
        stops.add(stop2);
        stops.add(stop3);
        stops.add(stop4);
    }
    
    @Test
    public void testConstructor() {
    	List<BusStop> favouriteStops = favourites.getFavourites();
    	assertEquals(0, favouriteStops.size());
        BusStop selected = favourites.getSelectedStop();
        assertEquals(null, selected);
    }
    
    @Test
    public void testAddFavourites() {
    	BusStop stop5 = null;
    	assertTrue(favourites.getFavourites().size() == 0);
    	assertFalse(favourites.addStop(stop5));  
    	assertTrue(favourites.getFavourites().size() == 0);
    	assertTrue(favourites.addStop(stop1));
    	assertTrue(favourites.getFavourites().size() == 1);
    	assertTrue(favourites.addStop(stop2));
    	assertTrue(favourites.addStop(stop3));
    	assertTrue(favourites.addStop(stop4));
    	assertFalse(favourites.addStop(stop1));
    	
    }
    
    
    
    @Test         // HELP - NOT WORKING THE WAY IT SHOULD - SECEOND ASSERTION SHOULD EQUAL 4!!!
    public void testGetFavourites() {
    	assertTrue(favourites.getFavourites().size() == 0);
    	favourites.addStop(stop1);
    	assertTrue(favourites.getFavourites().size() == 1);
    	favourites.addStop(stop2);
    	assertTrue(favourites.getFavourites().size() == 2);
    	favourites.addStop(stop3);
    	assertTrue(favourites.getFavourites().size() == 3);
    	favourites.addStop(stop4);
    	assertTrue(favourites.getFavourites().size() == 4);
    	assertEquals(favourites.getFavourites(), stops);
    }
    
    @Test
    public void testRemoveStop() {
    	assertTrue(favourites.getFavourites().size() == 0);
    	favourites.addStop(stop1);
    	assertTrue(favourites.getFavourites().size() == 1);
    	favourites.addStop(stop2);
    	assertTrue(favourites.getFavourites().size() == 2);
    	favourites.addStop(stop3);
    	assertTrue(favourites.getFavourites().size() == 3);
    	favourites.addStop(stop4);
    	assertTrue(favourites.getFavourites().size() == 4);
    }
    
    @Test
    public void testSetIndexOfSelected() { // HOW DO WE ACCESS INDEX?
    	
    }
    
    @Test
    public void testGetSelectedStop() {
    	assertTrue(favourites.getFavourites().size() == 0);
    	favourites.addStop(stop1);
    	assertTrue(favourites.getFavourites().size() == 1);
    	favourites.addStop(stop2);
    	assertTrue(favourites.getFavourites().size() == 2);
    	favourites.addStop(stop3);
    	assertTrue(favourites.getFavourites().size() == 3);
    	favourites.addStop(stop4);
    	assertTrue(favourites.getFavourites().size() == 4);
    	
    	assertEquals(favourites.getSelectedStop(), null);
    	
    	favourites.setIndexOfSelected(0);
    	assertEquals(favourites.getSelectedStop(), stop1);
    	
    	favourites.setIndexOfSelected(1);
    	assertEquals(favourites.getSelectedStop(), stop2);
    	
    	favourites.setIndexOfSelected(2);
    	assertEquals(favourites.getSelectedStop(), stop3);
    	
    	favourites.setIndexOfSelected(3);
    	assertEquals(favourites.getSelectedStop(), stop4);
    }
    
 
    

}
