package ilusr.persistencelib.configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import ilusr.core.datamanager.xml.XmlManager;


/**
 * 
 * @author Jeff Riggle
 *
 */
public class XmlConfigurationManager implements IPersistenceManager{

	private XmlManager xmlManager;
	private List<PersistXml> configurationObjects;
	
	//TODO: Consider should I be able to change my file save location?
	/**
	 * 
	 * @param location The location of the xml file.
	 * @throws TransformerConfigurationException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public XmlConfigurationManager(String location) throws TransformerConfigurationException, ParserConfigurationException, SAXException, IOException {
		this(new XmlManager(location), new ArrayList<PersistXml>());
	}
	
	/**
	 * 
	 * @param manager The @see XmlManager to read and write to disk.
	 * @param config List of @see XmlConfigurationObjects to be saved and read.
	 */
	public XmlConfigurationManager(XmlManager manager, List<PersistXml> config) {
		xmlManager = manager;
		configurationObjects = config;
	}
	
	/**
	 * 
	 * @return A list of @see PersistXml assoicated with this configuration object.
	 */
	public List<PersistXml> configurationObjects() {
		return configurationObjects;
	}
	
	/**
	 * 
	 * @param configObject The @see PersistXml to add to the file.
	 */
	public void addConfigurationObject(PersistXml configObject) {
		configurationObjects.add(configObject);
	}
	
	/**
	 * 
	 * @param configObjects List of @see PersistXml to be added to file.
	 */
	public void addConfigurationObjects(List<PersistXml> configObjects) {
		configurationObjects.addAll(configObjects);
	}
	
	/**
	 * 
	 * @param configObject The @see PersistXml to remove from this file.
	 */
	public void removeConfigurationObject(PersistXml configObject) {
		configurationObjects.remove(configObject);
	}
	
	/**
	 * 
	 * @param configObjects List of @see PersistXml to remove from this file.
	 */
	public void removeConfigurationObjects(List<PersistXml> configObjects) {
		configurationObjects.removeAll(configObjects);
	}
	
	/**
	 * Remove all @see PersistXml from this file.
	 */
	public void clearConfigurationObjects() {
		configurationObjects.clear();
	}
	
	@Override
	public void prepare() throws TransformerConfigurationException, ParserConfigurationException {
		//This seems to be a bit of a hack.
		xmlManager.createNewFile(xmlManager.saveLocation());
				
		Element rootObject;
		for (PersistXml config : configurationObjects) {
			rootObject = config.persist(xmlManager.generator());
			xmlManager.generator().addElementToRoot(rootObject);
		}
	}
	
	@Override
	public void save() throws TransformerException, ParserConfigurationException {	
		prepare();
		xmlManager.save();
	}

	@Override
	public void load() throws SAXException, IOException, TransformerConfigurationException, ParserConfigurationException {
		configurationObjects.clear();
		xmlManager.load();
		NodeList nodes = xmlManager.document().getChildNodes();
		
		Element child;
		
		for (int i = 0; i < nodes.getLength(); i ++) {
			if (!(nodes.item(i) instanceof Element)) continue;
			
			child = (Element)nodes.item(i);
			XmlConfigurationObject loadObject = new XmlConfigurationObject(child.getNodeName());
			loadObject.load(child);
			configurationObjects.add(loadObject);
		}
	}
}
