package ilusr.persistencelib.configuration;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

/**
 * 
 * @author Jeff Riggle
 *
 */
public interface IPersistenceManager {
	/**
	 * 
	 * Prepares the persistence object to be saved to disk.
	 * 
	 * @throws TransformerException 
	 * @throws ParserConfigurationException
	 */
	void prepare() throws TransformerConfigurationException, ParserConfigurationException;
	/**
	 * Saves the persistence object to disk.
	 * @throws TransformerException 
	 * @throws ParserConfigurationException 
	 */
	void save() throws TransformerException, ParserConfigurationException;
	/**
	 * Loads the persistence object from disk to memory.
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 * @throws TransformerConfigurationException 
	 */
	void load() throws SAXException, IOException, TransformerConfigurationException, ParserConfigurationException;
}
