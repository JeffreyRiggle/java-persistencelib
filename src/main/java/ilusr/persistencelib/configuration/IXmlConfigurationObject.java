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
	 * @return A list of @see ConfigurationProperty associated with this Xml Configuration object.
	 */
	List<ConfigurationProperty> configurationProperties();
	/**
	 * 
	 * @param property @see ConfigurationProperty to add to this Xml Configuration object.
	 */
	void addConfigurationProperty(ConfigurationProperty property);
	/**
	 * 
	 * @param properties List of @see ConfigurationProperty to add to this Xml Configuration object.
	 */
	void addConfigurationProperties(List<ConfigurationProperty> properties);
	/**
	 * 
	 * @param property @see ConfigurationProperty to remove from this Xml Configuration object.
	 */
	void removeConfigurationProperty(ConfigurationProperty property);
	/**
	 * 
	 * @param properties List of @see ConfigurationProperty to remove from this Xml Configuration object.
	 */
	void removeConfigurationProperties(List<ConfigurationProperty> properties);
	/**
	 * Remove all @see ConfigurationProperty from this Xml Configuration object.
	 */
	void clearConfigurationProperties();
	/**
	 * 
	 * @return List of @see PersistXml associated with this Xml Configuration object.
	 */
	List<PersistXml> children();
	/**
	 * 
	 * @param child @see PersistXml to add to this Xml Configuration object.
	 */
	void addChild(PersistXml child);
	/**
	 * 
	 * @param children List of @see PersistXml to add to this Xml Configuration object.
	 */
	void addChildren(List<PersistXml> children);
	/**
	 * 
	 * @param child @see PersistXml to remove from this Xml Configuration object.
	 */
	void removeChild(PersistXml child);
	/**
	 * 
	 * @param children List of @see PersistXml to remove from this Xml Configuration object.
	 */
	void removeChildren(List<PersistXml> children);
	/**
	 * Removes all @see PersistXml children from this Xml Configuration object.
	 */
	void clearChildren();
	/**
	 * 
	 * @return The value associated with this Xml Configuration object.
	 */
	<T> T value();
	/**
	 * 
	 * @param value The new value to associated with this Xml Configuration object.
	 */
	<T> void value(T value);
	/**
	 * 
	 * @param name The String name associated with the Configuration object.
	 * @return The child @see XmlConfigurationObject.
	 */
	XmlConfigurationObject getChildObject(String name);
	/**
	 * 
	 * @param name name The String name associated with the Configuration object.
	 * @return name The value associated with the Configuration object.
	 */
	<T> T getChildValue(String name);
}
