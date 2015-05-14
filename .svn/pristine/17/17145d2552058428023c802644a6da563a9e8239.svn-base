package ca.ubc.cpsc210.nextbus.translink;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import ca.ubc.cpsc210.exception.TranslinkException;
import ca.ubc.cpsc210.nextbus.model.BusRoute;
import ca.ubc.cpsc210.nextbus.model.BusStop;

/**
 * XML parser for bus stop information received from Translink service.
 */
public class BusStopParser extends AbstractTranslinkParser {
	
	private StringBuffer accumulator = new StringBuffer();
	private BusStop busStop;
	private String stopNum;
	private String locationDescription;
	private String latitude;
	private String longitude;
	private String routes;
	private String[] routesSplit;
	private Set<BusRoute> routeSet = new HashSet<BusRoute>();
	private Boolean tagStopNum = false;
	private Boolean tagName = false;
	private Boolean tagLat = false;
	private Boolean tagLon = false;
	private String codeBuffer;
	private String messageBuffer;
	
	
	/**
	 * Constructor
	 */
	public BusStopParser() {
		busStop = null;
	}
	
	/**
	 * Produces the bus stop parsed from XML data
	 * @return  bus stop
	 * @throws TranslinkException if bus stop could not be parsed from data.  Specifically
	 * when the bus stop number, name, latitude or longitude is missing.
	 */
	public BusStop getParsedStop() throws TranslinkException {
		if (busStop == null) 
			throw new TranslinkException(-1, "Unable to parse stop data.");
		return busStop;
	}
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		System.out.println("Start Element: " + qName);
		accumulator.setLength(0);
		if (qName.equalsIgnoreCase("Stop")){
			tagStopNum = false;
		    tagName = false;
	        tagLat = false;
		    tagLon = false;
		}
			
			
	}
	
	@Override
	public void endDocument() throws SAXException {
		super.endDocument();

	}
	
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		System.out.println("EndElement: " + qName + " value: " + accumulator.toString().trim());
		if (qName.equalsIgnoreCase("StopNo")) {
			stopNum = new String(accumulator);
			tagStopNum = true;
			// make an integer from this string 
		}
		if (qName.equalsIgnoreCase("Name")) {
			locationDescription = new String(accumulator);
			tagName = true;
		}
		if(qName.equalsIgnoreCase("Latitude")) {
			latitude = new String(accumulator);
			tagLat = true;
		}
		if(qName.equalsIgnoreCase("Longitude")) {
			longitude = new String(accumulator);
			tagLon = true;
		}
		if(qName.equalsIgnoreCase("Routes")) {
			routes = new String(accumulator);
			routesSplit = routes.split(", ");
			routeSet = new HashSet<BusRoute>();
			
			for (String s : routesSplit)
				routeSet.add(new BusRoute(s));
				
		}
		if(qName.equalsIgnoreCase("Stop")) {
			// DO WE NEED TO KNOW IF NULL?
			// if ((stopNum.length() == 0) || (locationDescription.length() == 0) || (latitude.length() == 0) || (longitude.length() == 0))
			// replaced above with below and switched results
			if (tagStopNum && tagLat && tagLon && tagName){
				busStop = new BusStop(Integer.parseInt(stopNum),  locationDescription, 
						Double.parseDouble(latitude), Double.parseDouble(longitude), routeSet);
			}
			else
				busStop = null;
		}
		if (qName.equalsIgnoreCase("Error")) {
			this.code = Integer.parseInt(codeBuffer);
			this.message = messageBuffer;
			this.isError = true;
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		accumulator.append(ch, start, length);
		
	}
	
}
