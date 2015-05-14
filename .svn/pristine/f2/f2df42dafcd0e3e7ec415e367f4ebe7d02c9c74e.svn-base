package ca.ubc.cpsc210.nextbus.translink;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import ca.ubc.cpsc210.nextbus.model.BusRoute;
import ca.ubc.cpsc210.nextbus.util.LatLon;
import ca.ubc.cpsc210.nextbus.util.Segment;


/**
 * Parser for KML files that contain bus route data received from Translink service.
 */
public class KMLParser extends DefaultHandler {
	private BusRoute route;
	private StringBuffer accumulator = new StringBuffer();
	private String north = new String();
	private String south = new String();
	private String east = new String();
	private String west = new String();

	
	// NOTE: you will likely need to add additional fields, in part to represent
	// data parsed from the xml file
	
	/**
	 * Constructor
	 * @param route  the route to which parsed route segments are to be added
	 */
	public KMLParser(BusRoute route) {
		this.route = route;
	}

	@Override
	public void endDocument() throws SAXException {
		System.out.println("End Document!");
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		System.out.println("Start Element: " + qName);
		accumulator.setLength(0);
	}
		
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		
		String coordinatesString;
		String[] coordinateSets;
		String[] point;
		
		System.out.println("EndElement: " + qName + " value: " + accumulator.toString().trim());	
		if (qName  == "LatLonAltBox") { // DID NOT MAKE SURE THAT QNAME IS LOWERCASE
		route.setBounds(Double.parseDouble(north), Double.parseDouble(south), 
				Double.parseDouble(east), Double.parseDouble(west));
		}
		if (qName.equalsIgnoreCase("north")) {
			north = new String(accumulator);
			System.out.println("north: " + north);	// Display the value of north *: not necessary
		}
		if (qName.equalsIgnoreCase("south")) {
			south = new String(accumulator);
			System.out.println("south: " + south); // Display the value of south *: not necessary
		}
		if (qName.equalsIgnoreCase("east")) {
			east = new String(accumulator);
			System.out.println("east: " + east); // Display the value of east *: not necessary
		}
		if (qName.equalsIgnoreCase("west")) {
			west = new String(accumulator);
			System.out.println("west: " + west); // Display the value of west *: not necessary
		}		
		if (qName.equalsIgnoreCase("coordinates")) { 
			route.addSegment(new Segment()); 
			    // add a new segment to the route
			coordinatesString = new String(accumulator);
				// store the coordinates as a string 
			coordinateSets = coordinatesString.split(" "); 
				// separates coordinates string into sets of points
			//for(int i=0; i<coordinatesArray.length; i++){
			for (String s : coordinateSets){
				point = s.split(",");
					// separates point string into longitude, latitude and altitude, respectively
				if (point.length == 3) {
					// only creates new point if there are 3 strings in point		
				LatLon latlon = new LatLon(point[1], point[0]);
					// create a new point using coordinates
				route.getSegments().get(route.getSegments().size()-1).addPoint(latlon);
					// add point to segment
				}
				}	
			}
		}
	
		

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		accumulator.append(ch, start, length);
	}
}


// This is the original endDocument() method from this KMLParser file.
// May need to replace above with this.

//	@Override
//	public void endDocument() throws SAXException {
//		super.endDocument();
		
//	}

	