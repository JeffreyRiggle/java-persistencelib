package UnitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import ilusr.persistencelib.configuration.ConfigurationProperty;


public class ConfigurationPropertyUnitTest {

	private final String _name1 = "Property1";
	private final String _value1 = "Value1";
	
	private final String _name2 = "Property2";
	private final String _value2 = "Value2";
	
	@Test
	public void testCreateConfigurationProperty() {
		ConfigurationProperty property = new ConfigurationProperty(_name1, _value1);
		
		assertEquals(_name1, property.name());
		assertEquals(_value1, property.value());
	}
	
	@Test
	public void testSetName() {
		ConfigurationProperty property = new ConfigurationProperty(_name1, _value1);
		assertEquals(_name1, property.name());
		
		property.name(_name2);
		assertEquals(_name2, property.name());
	}

	@Test
	public void testSetValue() {
		ConfigurationProperty property = new ConfigurationProperty(_name1, _value1);
		assertEquals(_value1, property.value());
		
		property.value(_value2);
		assertEquals(_value2, property.value());
	}
}
