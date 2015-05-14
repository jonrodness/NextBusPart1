package ca.ubc.cpsc210.nextbus.translink;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipInputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import ca.ubc.cpsc210.exception.TranslinkException;
import ca.ubc.cpsc210.nextbus.model.BusRoute;
import ca.ubc.cpsc210.nextbus.model.BusStop;
import ca.ubc.cpsc210.tests.TranslinkTests;

/**
 * A stub for the Translink web service.  Produces fixed data read from files
 * rather than communicating with Translink service.
 */
public class TranslinkStub extends AbstractTranslinkService {
    private List<File> dataFiles;
    
    /**
     * Constructor
     * 
     * @param dataFiles   list of files containing stub data
     */
    public TranslinkStub( List<File> dataFiles ) {
        this.dataFiles = dataFiles;
    }
    
    /* Note that this method reads wait time estimates from a file and
     * therefore always adds the same data to the given stop.  The particular
     * stop number is ignored. 
     */
    @Override
    public void addWaitTimeEstimatesToStop(BusStop stop)
            throws TranslinkException {
    
        try {
			InputSource is = getEstimatesInputSource();
			stop.clearWaitTimes();
			parseWaitTimesFromXML(is, stop);
		} catch (FileNotFoundException e) {
			// convert to TranslinkException
			throw new TranslinkException(-1, e.getMessage());
		}
    }
    
    /**
     * Gets input source for wait time estimates
     * @return  input source connected to file containing wait time estimates
     * @throws FileNotFoundException
     */
    private InputSource getEstimatesInputSource() throws FileNotFoundException {
    	InputStream in = new FileInputStream(dataFiles.get(TranslinkTests.STOP_ESTIMATES));
    	InputSource inputSource = new InputSource(new InputStreamReader(in));
    	return inputSource;
    }
    
    /* Note that this method reads bus locations read from a file and
     * therefore always adds the same data to the given stop.  The particular
     * stop number is ignored. 
     */
    @Override
    public void addBusLocationsForStop(BusStop stop) throws TranslinkException {
        try {
			InputSource is = getLocationsInputSource();
			stop.clearBusLocations();
			parseBusLocationsFromXML(is, stop);
		} catch (FileNotFoundException e) {
			// convert to TranslinkException
			throw new TranslinkException(-1, e.getMessage());
		}
    }
    
    /**
     * Gets input source for bus locations
     * @return  input source connected to file containing bus locations
     * @throws FileNotFoundException
     */
    private InputSource getLocationsInputSource() throws FileNotFoundException {
    	InputStream in = new FileInputStream(dataFiles.get(TranslinkTests.BUS_LOCATIONS));
    	InputSource inputSource = new InputSource(new InputStreamReader(in));
    	return inputSource;
    }
    
    /* Note that this method reads bus stop data read from a file and
     * therefore always produces the same BusStop object.  The particular
     * stop number is ignored. 
     */
    @Override
    public BusStop getBusStop(String stopNum) throws TranslinkException {
        try {
			InputSource is = getStopInputSource();
			return parseBusStopFromXML(is);
		} catch (FileNotFoundException e) {
			// convert to TranslinkException
			throw new TranslinkException(-1, e.getMessage());
		}
    }
    
    /**
     * Gets input source for bus stop 
     * @return  input source connected to file containing bus stop information
     * @throws FileNotFoundException
     */
    private InputSource getStopInputSource() throws FileNotFoundException {
    	InputStream in = new FileInputStream(dataFiles.get(TranslinkTests.BUS_STOP));
    	InputSource inputSource = new InputSource(new InputStreamReader(in));
    	return inputSource;
    }
    
    /* Note that this method reads bus route data read from a file and
     * therefore always produces the same data.  The particular map
     * URL in the BusRoute object passed as a parameter is ignored.
     */
    @Override
    public void parseKMZ(BusRoute route) throws TranslinkException {
        try {
            InputStream is = new FileInputStream(dataFiles.get(TranslinkTests.BUS_ROUTE));
            ZipInputStream zis = new ZipInputStream(is);
            zis.getNextEntry(); // assuming only one entry in zip file
            InputSource src = new InputSource(zis);
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser parser = spf.newSAXParser();

            XMLReader reader = parser.getXMLReader();

            KMLParser kmlParser = new KMLParser(route);
            reader.setContentHandler(kmlParser);
            reader.parse(src);
        } catch (Exception e) {
            // Convert other exception types to TranslinkException so clients do not
            // have to worry about the different possibilities.
            throw new TranslinkException(-1, "Unable to retrieve bus route");
        }
    }

}
