package UnitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;

import org.junit.Test;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import ilusr.core.datamanager.xml.XmlGenerator;
import ilusr.persistencelib.configuration.ConfigurationProperty;
import ilusr.persistencelib.configuration.PersistXml;
import ilusr.persistencelib.configuration.ValueTypes;
import ilusr.persistencelib.configuration.XmlConfigurationObject;

public class XmlConfigurationObjectUnitTest {

	private final String _videoConfiguration = "VideoConfiguration";
	private List<ConfigurationProperty> _videoConfigurationProperties;
	private List<PersistXml> _videoChildren;
	private final String _videoName = "Video";
	private final String _fullScreenName = "FullScreen";
	private final String _fullScreenValue = "False";
	
	private final String _resolutionName = "Resolution";
	private final String _widthName = "Width";
	private final int _widthValue = 800;
	private final String _heightName = "Height";
	private final int _heightValue = 600;
	
	private final String _testConfiguration = "TestConfiguration";
	private List<ConfigurationProperty> _testConfigurationProperties;
	private List<PersistXml> _testChildren;
	private final ConfigurationProperty _testProperty1 = new ConfigurationProperty("PropertyNameOne", "ValueOne");
	private final ConfigurationProperty _testProperty2 = new ConfigurationProperty("PropertyName2", "Value2");
	private final ConfigurationProperty _testProperty3 = new ConfigurationProperty("ThirdProperty", "ThirdValue");
	
	private XmlConfigurationObject _testChild1;
	private XmlConfigurationObject _testChild2;
	private XmlConfigurationObject _testChild3;
	private final String _testChildName1 = "FirstChild";
	private final String _testChildName2 = "Child2";
	private final String _testChildName3 = "ChildThree";
	private final String _testChildValue1 = "FirstValue";
	private final String _testChildValue2 = "Value2";
	private final String _testChildValue3 = "ValueThree";
	
	private XmlGenerator _generator;
	
	@Test
	public void testCreateMinimalXmlConfigurationObject() {
		try {
			XmlConfigurationObject configurationObject = new XmlConfigurationObject(_videoConfiguration);
			
			assertEquals(_videoConfiguration, configurationObject.name());
			assertNotNull(configurationObject.children());
			assertNotNull(configurationObject.configurationProperties());
		} catch (Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void testCreateFullXmlConfigurationObject() {
		try {
			initailizeLists();
			XmlConfigurationObject configurationObject = 
					new XmlConfigurationObject(_videoConfiguration, 
							_videoConfigurationProperties, _videoChildren, _videoName);
			
			assertEquals(_videoConfiguration, configurationObject.name());
			assertEquals(_videoConfigurationProperties, configurationObject.configurationProperties());
			assertEquals(_videoChildren, configurationObject.children());
			assertEquals(_videoName, configurationObject.value());
		} catch (Exception e) {
			fail(e.toString());
		}
	}

	@Test
	public void testSetName() {
		try {
			XmlConfigurationObject configurationObject = new XmlConfigurationObject(_videoConfiguration);
			assertEquals(_videoConfiguration, configurationObject.name());
			
			configurationObject.name(_widthName);
			assertEquals(_widthName, configurationObject.name());
		} catch (Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void testAddConfigurationProperty() {
		try {
			XmlConfigurationObject configurationObject = new XmlConfigurationObject(_testConfiguration);
			assertNotNull(configurationObject.configurationProperties());
			
			configurationObject.addConfigurationProperty(_testProperty1);
			assertEquals(1, configurationObject.configurationProperties().size());
			assertEquals(_testProperty1, configurationObject.configurationProperties().get(0));
			
			configurationObject.addConfigurationProperty(_testProperty2);
			assertEquals(2, configurationObject.configurationProperties().size());
			assertEquals(_testProperty2, configurationObject.configurationProperties().get(1));
			
			configurationObject.addConfigurationProperty(_testProperty3);
			assertEquals(3, configurationObject.configurationProperties().size());
			assertEquals(_testProperty3, configurationObject.configurationProperties().get(2));
		} catch (Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void testAddConfigurationProperties() {
		try {
			initializeTest();
			XmlConfigurationObject configurationObject = new XmlConfigurationObject(_testConfiguration);
			assertNotNull(configurationObject.configurationProperties());
			
			configurationObject.addConfigurationProperties(_testConfigurationProperties);
			assertEquals(3, configurationObject.configurationProperties().size());
			assertTrue(configurationObject.configurationProperties().contains(_testProperty1));
			assertTrue(configurationObject.configurationProperties().contains(_testProperty2));
			assertTrue(configurationObject.configurationProperties().contains(_testProperty3));
		} catch (Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void testRemoveConfigurationProperty() {
		try {
			initializeTest();
			//TODO: Change this to do a full creation.
			XmlConfigurationObject configurationObject = new XmlConfigurationObject(_testConfiguration);
			assertNotNull(configurationObject.configurationProperties());
		
			configurationObject.addConfigurationProperties(_testConfigurationProperties);
			assertEquals(3, configurationObject.configurationProperties().size());
			
			configurationObject.removeConfigurationProperty(_testProperty1);
			assertEquals(2, configurationObject.configurationProperties().size());
			assertFalse(configurationObject.configurationProperties().contains(_testProperty1));
			
			configurationObject.removeConfigurationProperty(_testProperty2);
			assertEquals(1, configurationObject.configurationProperties().size());
			assertFalse(configurationObject.configurationProperties().contains(_testProperty2));
			
			configurationObject.removeConfigurationProperty(_testProperty3);
			assertEquals(0, configurationObject.configurationProperties().size());
			assertFalse(configurationObject.configurationProperties().contains(_testProperty3));
		} catch (Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void testRemoveConfigurationProperties() {
		try {
			initializeTest();
			//TODO: Change this to do a full creation.
			XmlConfigurationObject configurationObject = new XmlConfigurationObject(_testConfiguration);
			assertNotNull(configurationObject.configurationProperties());
		
			configurationObject.addConfigurationProperties(_testConfigurationProperties);
			assertEquals(3, configurationObject.configurationProperties().size());
			
			configurationObject.removeConfigurationProperties(_testConfigurationProperties);
			assertEquals(0, configurationObject.configurationProperties().size());

			assertFalse(configurationObject.configurationProperties().contains(_testProperty1));
			assertFalse(configurationObject.configurationProperties().contains(_testProperty2));
			assertFalse(configurationObject.configurationProperties().contains(_testProperty3));
		} catch (Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void testClearConfigurationProperties() {
		try {
			initializeTest();
			//TODO: Change this to do a full creation.
			XmlConfigurationObject configurationObject = new XmlConfigurationObject(_testConfiguration);
			assertNotNull(configurationObject.configurationProperties());
		
			configurationObject.addConfigurationProperties(_testConfigurationProperties);
			assertEquals(3, configurationObject.configurationProperties().size());
			
			configurationObject.clearConfigurationProperties();
			assertEquals(0, configurationObject.configurationProperties().size());

			assertFalse(configurationObject.configurationProperties().contains(_testProperty1));
			assertFalse(configurationObject.configurationProperties().contains(_testProperty2));
			assertFalse(configurationObject.configurationProperties().contains(_testProperty3));
		} catch (Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void testAddChild() {
		try {
			initializeTest();
			XmlConfigurationObject configurationObject = new XmlConfigurationObject(_testConfiguration);
			assertNotNull(configurationObject.children());
			
			configurationObject.addChild(_testChild1);
			assertEquals(1, configurationObject.children().size());
			assertTrue(configurationObject.children().contains(_testChild1));
			
			configurationObject.addChild(_testChild2);
			assertEquals(2, configurationObject.children().size());
			assertTrue(configurationObject.children().contains(_testChild2));
			
			configurationObject.addChild(_testChild3);
			assertEquals(3, configurationObject.children().size());
			assertTrue(configurationObject.children().contains(_testChild3));
		} catch (Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void testAddChildren() {
		try {
			initializeTest();
			XmlConfigurationObject configurationObject = new XmlConfigurationObject(_testConfiguration);
			assertNotNull(configurationObject.children());
			
			configurationObject.addChildren(_testChildren);
			assertEquals(3, configurationObject.children().size());
			assertTrue(configurationObject.children().contains(_testChild1));
			assertTrue(configurationObject.children().contains(_testChild2));
			assertTrue(configurationObject.children().contains(_testChild3));
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void testRemoveChild() {
		try {
			initializeTest();
			XmlConfigurationObject configurationObject = new XmlConfigurationObject(_testConfiguration);
			
			configurationObject.addChildren(_testChildren);
			assertEquals(3, configurationObject.children().size());
			
			configurationObject.removeChild(_testChild1);
			assertEquals(2, configurationObject.children().size());
			assertFalse(configurationObject.children().contains(_testChild1));
			
			configurationObject.removeChild(_testChild2);
			assertEquals(1, configurationObject.children().size());
			assertFalse(configurationObject.children().contains(_testChild2));
			
			configurationObject.removeChild(_testChild3);
			assertEquals(0, configurationObject.children().size());
			assertFalse(configurationObject.children().contains(_testChild3));
		} catch (Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void testRemoveChildren() {
		try {
			initializeTest();
			XmlConfigurationObject configurationObject = new XmlConfigurationObject(_testConfiguration);
			
			configurationObject.addChildren(_testChildren);
			assertEquals(3, configurationObject.children().size());
			
			configurationObject.removeChildren(_testChildren);
			assertEquals(0, configurationObject.children().size());
			assertFalse(configurationObject.children().contains(_testChild1));
			assertFalse(configurationObject.children().contains(_testChild2));
			assertFalse(configurationObject.children().contains(_testChild3));
			
		} catch (Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void testClearChildren() {
		try {
			initializeTest();
			XmlConfigurationObject configurationObject = new XmlConfigurationObject(_testConfiguration);
			
			configurationObject.addChildren(_testChildren);
			assertEquals(3, configurationObject.children().size());
			
			configurationObject.clearChildren();
			assertEquals(0, configurationObject.children().size());
			assertFalse(configurationObject.children().contains(_testChild1));
			assertFalse(configurationObject.children().contains(_testChild2));
			assertFalse(configurationObject.children().contains(_testChild3));
			
		} catch (Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void testSetValue() {
		try {
			XmlConfigurationObject configurationObject = new XmlConfigurationObject(_testConfiguration);
			configurationObject.value(_testChildName1);
			assertEquals(_testChildName1, configurationObject.value());
			
			configurationObject.value(_testChildName2);
			assertEquals(_testChildName2, configurationObject.value());
			
			configurationObject.value(_testChildName3);
			assertEquals(_testChildName3, configurationObject.value());
		} catch (Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void testPersist() {
		try {
			initializeTest();
			initializeGenerators();
			XmlConfigurationObject configurationObject = 
					new XmlConfigurationObject(_testConfiguration,
							_testConfigurationProperties, _testChildren, null);
			
			Node persistedXml = configurationObject.persist(_generator);
			assertNotNull(persistedXml);
			//TODO: update this test
		} catch (Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void testLoad() {
		try {
			initializeTest();
			initializeGenerators();
			XmlConfigurationObject configurationObject = 
					new XmlConfigurationObject(_testConfiguration,
							_testConfigurationProperties, _testChildren, null);
			
			Element persistedXml = configurationObject.persist(_generator);
			
			XmlConfigurationObject result = new XmlConfigurationObject(_testConfiguration);
			result.load(persistedXml);
			
			assertNotNull(result);
			
			assertEquals(3, result.configurationProperties().size());
			
			assertEquals(3, result.children().size());
		} catch (Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void testGetChildObject() {
		try {
			initializeTest();
			initializeGenerators();
			XmlConfigurationObject configurationObject = 
					new XmlConfigurationObject(_testConfiguration,
							_testConfigurationProperties, _testChildren, null);
			
			XmlConfigurationObject child1 = configurationObject.getChildObject(_testChildName1);
			assertEquals(_testChild1, child1);
			
			XmlConfigurationObject child2 = configurationObject.getChildObject(_testChildName2);
			assertEquals(_testChild2, child2);
			
			XmlConfigurationObject child3 = configurationObject.getChildObject(_testChildName3);
			assertEquals(_testChild3, child3);
		} catch (Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void testGetChildValue() {
		try {
			initializeTest();
			initializeGenerators();
			XmlConfigurationObject configurationObject = 
					new XmlConfigurationObject(_testConfiguration,
							_testConfigurationProperties, _testChildren, null);
			
			String childVal1 = configurationObject.getChildValue(_testChildName1);
			assertEquals(_testChildValue1, childVal1);
			
			String childVal2 = configurationObject.getChildValue(_testChildName2);
			assertEquals(_testChildValue2, childVal2);
			
			String childVal3 = configurationObject.getChildValue(_testChildName3);
			assertEquals(_testChildValue3, childVal3);
		} catch (Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void testGetValueType() {
		try {
			XmlConfigurationObject config1 = new XmlConfigurationObject(_widthName);
			config1.value(_widthValue);
			
			assertEquals(ValueTypes.IntegerType, config1.getValueType());
			
			XmlConfigurationObject config2 = new XmlConfigurationObject(_testChildName1);
			config2.value(_testChildValue1);
			
			assertEquals(ValueTypes.StringType, config2.getValueType());
			
		} catch (Exception e) {
			fail(e.toString());
		}
	}
	
	private void initailizeLists() throws TransformerConfigurationException, ParserConfigurationException {
		_videoConfigurationProperties = new ArrayList<ConfigurationProperty>();
		_videoConfigurationProperties.add(new ConfigurationProperty(_fullScreenName, _fullScreenValue));
		
		_videoChildren = new ArrayList<PersistXml>();
		
		XmlConfigurationObject resolutionChild = new XmlConfigurationObject(_resolutionName);
		XmlConfigurationObject widthChild = new XmlConfigurationObject(_widthName, _widthValue);
		XmlConfigurationObject heightChild = new XmlConfigurationObject(_heightName, _heightValue);
		resolutionChild.addChild(widthChild);
		resolutionChild.addChild(heightChild);
		
		_videoChildren.add(resolutionChild);
	}
	
	private void initializeTest() throws TransformerConfigurationException, ParserConfigurationException {
		_testConfigurationProperties = new ArrayList<ConfigurationProperty>();
		_testConfigurationProperties.add(_testProperty1);
		_testConfigurationProperties.add(_testProperty2);
		_testConfigurationProperties.add(_testProperty3);
		
		_testChildren = new ArrayList<PersistXml>();
		_testChild1 = new XmlConfigurationObject(_testChildName1, _testChildValue1);
		_testChild2 = new XmlConfigurationObject(_testChildName2, _testChildValue2);
		_testChild3 = new XmlConfigurationObject(_testChildName3, _testChildValue3);
		
		_testChildren.add(_testChild1);
		_testChildren.add(_testChild2);
		_testChildren.add(_testChild3);
	}
	
	private void initializeGenerators() throws TransformerConfigurationException, ParserConfigurationException {
		_generator = new XmlGenerator();
	}
}
