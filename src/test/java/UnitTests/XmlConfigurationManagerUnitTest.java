import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

import ilusr.core.datamanager.xml.XmlManager;
import ilusr.core.io.XMLDocumentUtilities;
import ilusr.persistencelib.configuration.ConfigurationProperty;
import ilusr.persistencelib.configuration.PersistXml;
import ilusr.persistencelib.configuration.XmlConfigurationManager;
import ilusr.persistencelib.configuration.XmlConfigurationObject;

public class XmlConfigurationManagerUnitTest {

	private final String _file1 = System.getProperty("user.home") + "/ilusr/UnitTests/configmanagerunittest1.xml";
	private final String _file2 = System.getProperty("user.home") + "/ilusr/UnitTests/configmanagerunittest2.xml";
	
	private XmlManager _xmlManager;
	private List<PersistXml> _objects;
	private XmlConfigurationObject _parentObject;
	private XmlConfigurationObject _object1;
	private XmlConfigurationObject _object2;
	private XmlConfigurationObject _object3;
	private XmlConfigurationObject _object4;
	
	private List<PersistXml> _castObjects;
	private XmlConfigurationObject _parentCastObjectTest;
	private XmlConfigurationObject _stringTestObject;
	private XmlConfigurationObject _booleanTestObject;
	private XmlConfigurationObject _integerTestObject;
	private XmlConfigurationObject _floatTestObject;
	private XmlConfigurationObject _doubleTestObject;
	
	private final String _parentName = "ParentObject";
	private final String _object1Name = "FirstObject";
	private final ConfigurationProperty _parentProperty = new ConfigurationProperty("SomeAttrib", "SomeVal");
	private final String _object1Value = "BLUE";
	private final String _object2Name = "SomeObject";
	private final String _object2Value = "CHEESE";
	private final String _object3Name = "Object";
	private final String _object3Value = "Magic";
	private final String _object4Name = "APPA";
	private final String _object4Value = "PIE";
	private final ConfigurationProperty _objectProperty = new ConfigurationProperty("AOIEL", "ab^&!@");
	
	private final String _parentCastObjectName = "CastTesting";
	private final String _stringObjectName = "StringValue";
	private final String _stringObjectValue = "MAGICAL";
	private final String _booleanObjectName = "BooleanValue";
	private final boolean _booleanObjectValue = true;
	private final String _integerObjectName = "IntegerValue";
	private final int _integerObjectValue = 32;
	private final String _floatObjectName = "FloatValue";
	private final float _floatObjectValue = 2.01f;
	private final String _doubleObjectName = "DoubleValue";
	private final double _doubleObjectValue = 3.451;
	
	private final String _fileContents = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><ParentObject SomeAttrib=\"SomeVal\"><FirstObject ValueType=\"string\">BLUE</FirstObject><SomeObject ValueType=\"string\">CHEESE</SomeObject><Object ValueType=\"string\">Magic</Object><APPA AOIEL=\"ab^&amp;!@\" ValueType=\"string\">PIE</APPA></ParentObject>";
	private final String _file2Contents = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><CastTesting><StringValue ValueType=\"string\">MAGICAL</StringValue><BooleanValue ValueType=\"bool\">true</BooleanValue><IntegerValue ValueType=\"int\">32</IntegerValue><FloatValue ValueType=\"float\">2.01</FloatValue><DoubleValue ValueType=\"double\">3.451</DoubleValue></CastTesting>";
	
	@Test
	public void testCreateXmlConfigurationManagerMinimal() {
		try {
			XmlConfigurationManager manager = new XmlConfigurationManager(_file1);
			assertNotNull(manager.configurationObjects());
		} catch (Exception e) {
			fail(e.toString());
		}
	}

	@Test
	public void testCreateXmlConfigurationObjectFull() {
		try {
			initialize();
			XmlConfigurationManager manager = new XmlConfigurationManager(_xmlManager, _objects);
			assertNotNull(manager.configurationObjects());
			assertEquals(1, manager.configurationObjects().size());
		} catch (Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void testAddConfigurationObject() {
		try {
			initialize();
			XmlConfigurationManager manager = new XmlConfigurationManager(_file1);
			assertNotNull(manager.configurationObjects());
			assertEquals(0, manager.configurationObjects().size());
			
			manager.addConfigurationObject(_object1);
			assertEquals(1, manager.configurationObjects().size());
			manager.addConfigurationObject(_object2);
			assertEquals(2, manager.configurationObjects().size());
			manager.addConfigurationObject(_object3);
			assertEquals(3, manager.configurationObjects().size());
			manager.addConfigurationObject(_object4);
			assertEquals(4, manager.configurationObjects().size());
		} catch (Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void testAddConfigurationObjects() {
		try {
			initialize();
			XmlConfigurationManager manager = new XmlConfigurationManager(_file1);
			assertNotNull(manager.configurationObjects());
			assertEquals(0, manager.configurationObjects().size());
			
			List<PersistXml> objs = new ArrayList<PersistXml>();
			objs.add(_object1);
			objs.add(_object2);
			objs.add(_object3);
			objs.add(_object4);
			
			manager.addConfigurationObjects(objs);
			assertEquals(4, manager.configurationObjects().size());
		} catch (Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void testRemoveConfigurationObject() {
		try {
			initialize();
			XmlConfigurationManager manager = new XmlConfigurationManager(_file1);
			assertNotNull(manager.configurationObjects());
			assertEquals(0, manager.configurationObjects().size());
			
			List<PersistXml> objs = new ArrayList<PersistXml>();
			objs.add(_object1);
			objs.add(_object2);
			objs.add(_object3);
			objs.add(_object4);
			
			manager.addConfigurationObjects(objs);
			assertEquals(4, manager.configurationObjects().size());
			
			manager.removeConfigurationObject(_object1);
			assertEquals(3, manager.configurationObjects().size());
			manager.removeConfigurationObject(_object2);
			assertEquals(2, manager.configurationObjects().size());
			manager.removeConfigurationObject(_object3);
			assertEquals(1, manager.configurationObjects().size());
			manager.removeConfigurationObject(_object4);
			assertEquals(0, manager.configurationObjects().size());
		} catch (Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void testRemoveConfigurationObjects() {
		try {
			initialize();
			XmlConfigurationManager manager = new XmlConfigurationManager(_file1);
			assertNotNull(manager.configurationObjects());
			assertEquals(0, manager.configurationObjects().size());
			
			List<PersistXml> objs = new ArrayList<PersistXml>();
			objs.add(_object1);
			objs.add(_object2);
			objs.add(_object3);
			objs.add(_object4);
			
			manager.addConfigurationObjects(objs);
			assertEquals(4, manager.configurationObjects().size());
			
			manager.removeConfigurationObjects(objs);
			assertEquals(0, manager.configurationObjects().size());
		} catch (Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void testClearConfigurationObjects() {
		try {
			initialize();
			XmlConfigurationManager manager = new XmlConfigurationManager(_file1);
			assertNotNull(manager.configurationObjects());
			assertEquals(0, manager.configurationObjects().size());
			
			List<PersistXml> objs = new ArrayList<PersistXml>();
			objs.add(_object1);
			objs.add(_object2);
			objs.add(_object3);
			objs.add(_object4);
			
			manager.addConfigurationObjects(objs);
			assertEquals(4, manager.configurationObjects().size());
			
			manager.clearConfigurationObjects();
			assertEquals(0, manager.configurationObjects().size());
		} catch (Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void testPrepare() {
		try {
			initialize();
			XmlManager xmlManager = new XmlManager(_file1);
			XmlConfigurationManager manager = new XmlConfigurationManager(xmlManager, new ArrayList<PersistXml>());
			
			manager.addConfigurationObject(_parentObject);
			manager.prepare();
			
			assertEquals(_fileContents, XMLDocumentUtilities.convertDocumentToString(xmlManager.document()));
		} catch (Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void testSave() {
		try {
			initialize();
			XmlConfigurationManager manager = new XmlConfigurationManager(_file1);
			
			manager.addConfigurationObject(_parentObject);
			manager.save();
			
			File file = new File(_file1);
			
			if (!file.exists()) {
				fail("File not found");
			}
			
			String content = getFileContent(file);
			assertEquals(_fileContents, content);
		} catch (Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void testCastedSave() {
		try {
			initializeCast();
			XmlConfigurationManager manager = new XmlConfigurationManager(_file2);
			
			manager.addConfigurationObject(_parentCastObjectTest);
			manager.save();
			
			File file = new File(_file2);
			
			if (!file.exists()) {
				fail("File not found");
			}
			
			String content = getFileContent(file);
			assertEquals(_file2Contents, content);
		} catch (Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void testLoad() {
		try {
			initialize();
			XmlConfigurationManager manager = new XmlConfigurationManager(_file1);
			
			manager.addConfigurationObject(_parentObject);
			manager.save();
			
			XmlConfigurationManager manager2 = new XmlConfigurationManager(_file1);
			
			manager2.load();
			assertNotNull(manager2.configurationObjects());
			XmlConfigurationObject rootObject = (XmlConfigurationObject)manager2.configurationObjects().get(0);
			
			assertEquals(4, rootObject.children().size());
			assertEquals(_parentProperty.name(), rootObject.configurationProperties().get(0).name());
			assertEquals(_parentProperty.value(), rootObject.configurationProperties().get(0).value());
			
			for (PersistXml loadedObject : rootObject.children()) {
				XmlConfigurationObject obj = (XmlConfigurationObject)loadedObject;
				
				switch (obj.name()) {
					case _object1Name:
						assertEquals(_object1Value, obj.value());
						break;
					case _object2Name:
						assertEquals(_object2Value, obj.value());
						break;
					case _object3Name:
						assertEquals(_object3Value, obj.value());
						break;
					case _object4Name:
						assertEquals(_object4Value, obj.value());
						assertEquals(_objectProperty.name(), obj.configurationProperties().get(0).name());
						assertEquals(_objectProperty.value(), obj.configurationProperties().get(0).value());
						break;
					default: 
						fail("invalid object.");
				}
			}
		} catch (Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void testCastLoad() {
		try {
			XmlConfigurationManager manager = new XmlConfigurationManager(_file2);
			
			manager.load();
			assertNotNull(manager.configurationObjects());
			XmlConfigurationObject rootObject = (XmlConfigurationObject)manager.configurationObjects().get(0);
			
			assertEquals(5, rootObject.children().size());
			
			for (PersistXml loadedObject : rootObject.children()) {
				XmlConfigurationObject obj = (XmlConfigurationObject)loadedObject;
				
				switch (obj.name()) {
					case _stringObjectName:
						assertEquals(_stringObjectValue, obj.value());
						assertTrue(obj.value() instanceof String);
						break;
					case _booleanObjectName:
						assertEquals(_booleanObjectValue, obj.value());
						assertTrue(obj.value() instanceof Boolean);
						break;
					case _integerObjectName:
						assertEquals(_integerObjectValue, obj.<Object>value());
						assertTrue(obj.value() instanceof Integer);
						break;
					case _floatObjectName:
						assertEquals(_floatObjectValue, obj.<Object>value());
						assertTrue(obj.value() instanceof Float);
						break;
					case _doubleObjectName:
						assertEquals(_doubleObjectValue, obj.<Object>value());
						assertTrue(obj.value() instanceof Double);
						break;
					default: 
						fail("invalid object.");
				}
			}
		} catch (Exception e) {
			fail(e.toString());
		}
	}
	
	private void initialize() throws TransformerConfigurationException, ParserConfigurationException, SAXException, IOException {
		_xmlManager = new XmlManager(_file1);
		_objects = new ArrayList<PersistXml>();
		_parentObject = new XmlConfigurationObject(_parentName);
		_parentObject.addConfigurationProperty(_parentProperty);
		_object1 = new XmlConfigurationObject(_object1Name, _object1Value);
		_object2 = new XmlConfigurationObject(_object2Name, _object2Value);
		_object3 = new XmlConfigurationObject(_object3Name, _object3Value);
		_object4 = new XmlConfigurationObject(_object4Name, _object4Value);
		_object4.addConfigurationProperty(_objectProperty);
		
		_parentObject.addChild(_object1);
		_parentObject.addChild(_object2);
		_parentObject.addChild(_object3);
		_parentObject.addChild(_object4);
		
		_objects.add(_parentObject);
	}
	
	private void initializeCast() throws TransformerConfigurationException, ParserConfigurationException, SAXException, IOException {
		_xmlManager = new XmlManager(_file2);
		_castObjects = new ArrayList<PersistXml>();
		_parentCastObjectTest = new XmlConfigurationObject(_parentCastObjectName);
		_stringTestObject = new XmlConfigurationObject(_stringObjectName, _stringObjectValue);
		_booleanTestObject = new XmlConfigurationObject(_booleanObjectName, _booleanObjectValue);
		_integerTestObject = new XmlConfigurationObject(_integerObjectName, _integerObjectValue);
		_floatTestObject = new XmlConfigurationObject(_floatObjectName, _floatObjectValue);
		_doubleTestObject = new XmlConfigurationObject(_doubleObjectName, _doubleObjectValue);
		
		_parentCastObjectTest.addChild(_stringTestObject);
		_parentCastObjectTest.addChild(_booleanTestObject);
		_parentCastObjectTest.addChild(_integerTestObject);
		_parentCastObjectTest.addChild(_floatTestObject);
		_parentCastObjectTest.addChild(_doubleTestObject);
		
		_castObjects.add(_parentCastObjectTest);
	}
	
	private String getFileContent(File file) throws IOException {
		String output = new String();
		FileInputStream inputStream = new FileInputStream(file);
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		String line = new String();
		
		while((line = reader.readLine()) != null) {
			output += line;
		}
		
		reader.close();
		return output;
	}
}
