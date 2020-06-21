package ilusr.persistencelib.configuration;

import java.util.List;

/**
 * 
 * @author Jeff Riggle
 *
 */
public interface IXmlConfigurationObject {
	/**
	 * 
	 * @return The name of the Xml Configuration object.
	 */
	String name();
	/**
	 * 
	 * @param name The new name for the Xml Configuration object.
	 */
	void name(String name);
	/**
	 * 
	 * @return A list of ConfigurationProperty associated with this Xml Configuration object.
	 */
	List<ConfigurationProperty> configurationProperties();
	/**
	 * 
	 * @param property ConfigurationProperty to add to this Xml Configuration object.
	 */
	void addConfigurationProperty(ConfigurationProperty property);
	/**
	 * 
	 * @param properties List of ConfigurationProperty to add to this Xml Configuration object.
	 */
	void addConfigurationProperties(List<ConfigurationProperty> properties);
	/**
	 * 
	 * @param property ConfigurationProperty to remove from this Xml Configuration object.
	 */
	void removeConfigurationProperty(ConfigurationProperty property);
	/**
	 * 
	 * @param properties List of ConfigurationProperty to remove from this Xml Configuration object.
	 */
	void removeConfigurationProperties(List<ConfigurationProperty> properties);
	/**
	 * Remove all ConfigurationProperties from this Xml Configuration object.
	 */
	void clearConfigurationProperties();
	/**
	 * 
	 * @return List of PersistXml associated with this Xml Configuration object.
	 */
	List<PersistXml> children();
	/**
	 * 
	 * @param child PersistXml to add to this Xml Configuration object.
	 */
	void addChild(PersistXml child);
	/**
	 * 
	 * @param children List of PersistXml to add to this Xml Configuration object.
	 */
	void addChildren(List<PersistXml> children);
	/**
	 * 
	 * @param child PersistXml to remove from this Xml Configuration object.
	 */
	void removeChild(PersistXml child);
	/**
	 * 
	 * @param children List of PersistXml to remove from this Xml Configuration object.
	 */
	void removeChildren(List<PersistXml> children);
	/**
	 * Removes all PersistXml children from this Xml Configuration object.
	 */
	void clearChildren();
	/**
	 * 
	 * @param <T> type of object to get.
	 * @return The value associated with this Xml Configuration object.
	 */
	<T> T value();
	/**
	 * 
	 * @param <T> type of object to set.
	 * @param value The new value to associated with this Xml Configuration object.
	 */
	<T> void value(T value);
	/**
	 * 
	 * @param name The String name associated with the Configuration object.
	 * @return The child XmlConfigurationObject.
	 */
	XmlConfigurationObject getChildObject(String name);
	/**
	 * 
	 * @param <T> type of object to get.
	 * @param name name The String name associated with the Configuration object.
	 * @return name The value associated with the Configuration object.
	 */
	<T> T getChildValue(String name);
}
