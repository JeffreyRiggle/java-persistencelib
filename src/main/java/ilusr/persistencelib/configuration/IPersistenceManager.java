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
	 * @throws TransformerConfigurationException Throws a Transformer configuration execption when transform fails 
	 * @throws ParserConfigurationException Throws a ParserConfigurationExecption when content cannot be parsed
	 */
	void prepare() throws TransformerConfigurationException, ParserConfigurationException;
	/**
	 * Saves the persistence object to disk.
	 * @throws TransformerException Throws a Transformer exception when transform fails 
	 * @throws ParserConfigurationException Throws a ParserConfigurationExecption when content cannot be parsed
	 */
	void save() throws TransformerException, ParserConfigurationException;
	/**
	 * Loads the persistence object from disk to memory.
	 * @throws IOException Throws an IOException when loading the file fails
	 * @throws SAXException Throws a SAXException
	 * @throws ParserConfigurationException Throws a ParserConfigurationExecption when content cannot be parsed
	 * @throws TransformerConfigurationException Throws a Transformer configuration execption when transform fails
	 */
	void load() throws SAXException, IOException, TransformerConfigurationException, ParserConfigurationException;
}
