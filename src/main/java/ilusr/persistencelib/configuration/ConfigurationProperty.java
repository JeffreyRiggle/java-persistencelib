package ilusr.persistencelib.configuration;

/**
 * 
 * @author Jeff Riggle
 *
 */
public class ConfigurationProperty {

	private String name;
	private String value;
	
	/**
	 * 
	 * @param name The name of the Configuration Property.
	 * @param value The value for the Configuration Property.
	 */
	public ConfigurationProperty(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	/**
	 * 
	 * @return The name of the Configuration Property.
	 */
	public String name() {
		return name;
	}
	
	/**
	 * 
	 * @param value The new name for the Configuration Property.
	 */
	public void name(String value) {
		name = value;
	}
	
	/**
	 * 
	 * @return The value of the Configuration Property.
	 */
	public String value() {
		return value;
	}
	
	/**
	 * 
	 * @param value The new value for the Configuration Property.
	 */
	public void value(String value) {
		this.value = value;
	}
}
