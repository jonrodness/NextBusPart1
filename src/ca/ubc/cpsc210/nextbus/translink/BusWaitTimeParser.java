package ca.ubc.cpsc210.nextbus.translink;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import ca.ubc.cpsc210.nextbus.model.BusRoute;
import ca.ubc.cpsc210.nextbus.model.BusStop;
import ca.ubc.cpsc210.nextbus.model.BusWaitTime;

/**
 * XML parser for bus wait time estimates received from Translink service.
 */
public class BusWaitTimeParser extends AbstractTranslinkParser {
	private BusStop busStop;
	private BusWaitTime waitTime;
	private BusRoute route;
	private StringBuffer accumulator = new StringBuffer();
	private String routeName;
	private String estimate;
	private String cancelledTrip;
	private String cancelledStop;
	private Boolean waitTag = false;
	private String codeBuffer;
	private String messageBuffer;
	private String url;
	
	
	/**
	 * Constructor
	 * 
	 * @param busStop  the bus stop to which wait time estimates must be added
	 */
	public BusWaitTimeParser(BusStop busStop) {
		this.busStop = busStop;
		isError = false;
	}
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		System.out.println("Start Element: " + qName);
		accumulator.setLength(0);
		if(qName.equalsIgnoreCase("NextBuses"))
			url = new String(attributes.getValue("NextBuses"));
		if (qName.equalsIgnoreCase("Schedule")) {
			waitTag = false;
		}
		
	}
	
	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
		
	}
	
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if(qName.equalsIgnoreCase("RouteNo")) {
			routeName = new String(accumulator);
			route = new BusRoute (routeName);
			route.setMapURL(url);
		}
		if(qName.equalsIgnoreCase("ExpectedCountdown")) {
			estimate = new String(accumulator);
			waitTag = true;
		}
		if(qName.equalsIgnoreCase("CancelledTrip")) {
			cancelledTrip = new String(accumulator);				
		}
		if(qName.equalsIgnoreCase("CancelledStop")) {
			cancelledStop = new String(accumulator);
		}
		if(qName.equalsIgnoreCase("Schedule")) {
			if ((busStop.getRouteNamed(route.getName()) != null) && waitTag) {
			// if busStop contains busRoute as specified by busLocation and estimate is specified, create a new waitTime and add to busStop
			if ((cancelledTrip.equals("false")) && (cancelledStop.equals("false"))) {
				waitTime = new BusWaitTime(route, Integer.parseInt(estimate), false);
			    busStop.addWaitTime(waitTime);
			    }
			else {
				waitTime = new BusWaitTime(route, Integer.parseInt(estimate), true);
			    busStop.addWaitTime(waitTime);
			    }
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
