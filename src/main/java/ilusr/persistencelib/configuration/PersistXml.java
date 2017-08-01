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
	 * @param generator A @see XmlGenerator to save xml objects to.
	 * @return The @see Element created in the @see XmlGenerator.
	 */
	Element persist(XmlGenerator generator);
	/**
	 * 
	 * @param xmlObject The @see Element to load into memory.
	 */
	void load(Element xmlObject);
}
