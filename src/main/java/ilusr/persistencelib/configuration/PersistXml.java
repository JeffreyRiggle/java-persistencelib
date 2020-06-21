package ilusr.persistencelib.configuration;

import org.w3c.dom.Element;

import ilusr.core.datamanager.xml.XmlGenerator;


/**
 * 
 * @author Jeff Riggle
 *
 */
public interface PersistXml {
	/**
	 * 
	 * @param generator A XmlGenerator to save xml objects to.
	 * @return The Element created in the XmlGenerator.
	 */
	Element persist(XmlGenerator generator);
	/**
	 * 
	 * @param xmlObject The Element to load into memory.
	 */
	void load(Element xmlObject);
}
