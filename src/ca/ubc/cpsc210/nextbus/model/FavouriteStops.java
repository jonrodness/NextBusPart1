package ca.ubc.cpsc210.nextbus.model;

import java.util.ArrayList;
import ca.ubc.cpsc210.nextbus.model.BusStop;

/**
 * Favourite bus stops
 * 
 * Design Pattern: Singleton
 */
public class FavouriteStops {
	private ArrayList<BusStop> stops;
	private int indexOfSelected;
	private static FavouriteStops instance;

	/**
	 * Constructor
	 * 		List of favourite stops is empty and no item is selected.
	 */
	private FavouriteStops() {
		indexOfSelected = -1;
		stops = new ArrayList<BusStop>();
	}

	/**
	 * Gets single instance of this class
	 * @return instance (the only one) of FavouriteStops
	 */
	public static FavouriteStops getInstance() {
		if (instance == null) {
			instance = new FavouriteStops();
		}
		
		return instance;
	}
	
	
	public boolean addStop(BusStop stop) { // HELP - MAY NEED EXCEPTIONS
		if (stop != null) {
			if (!instance.getFavourites().contains(stop)) {
			instance.getFavourites().add(stop);
			return true;
			}
			else
				return false;
		}	
		return false;
	}
	
	
	public void removeStop(BusStop stop) { // DEFINITELY NEED THROW EXCEPTION HERE IF STOP IS NOT ON LIST
		instance.getFavourites().remove(stop);
		instance.setIndexOfSelected(-1);
	}
	
	public java.util.ArrayList<BusStop> getFavourites() {
		return stops;
	}
	
	public void setIndexOfSelected(int indexOfSelected) { // THROW EXCEPTION HERE 
		this.indexOfSelected = indexOfSelected;
	}
	
	public BusStop getSelectedStop() {
		if ((indexOfSelected < instance.getFavourites().size()) && indexOfSelected >= 0)
		return instance.getFavourites().get(indexOfSelected);
		else
		return null;
	}
	
	public void clear() {
		instance.getFavourites().clear();
	}

}
