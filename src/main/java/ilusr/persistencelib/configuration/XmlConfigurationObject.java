package ilusr.persistencelib.configuration;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ilusr.core.datamanager.xml.XmlGenerator;


/**
 * 
 * @author Jeff Riggle
 *
 */
public class XmlConfigurationObject implements PersistXml, IXmlConfigurationObject{

	private final String VALUE_TYPE = "ValueType";
	private String name;
	private List<ConfigurationProperty> configurationProperties;
	private List<PersistXml> children;
	private Object value;
	
	/**
	 * 
	 * @param name The name of the configuration object.
	 * @throws TransformerConfigurationException
	 * @throws ParserConfigurationException
	 */
	public XmlConfigurationObject(String name) throws TransformerConfigurationException, ParserConfigurationException {
		this(name, null);
	}
	
	/**
	 * 
	 * @param name The name of the configuration object.
	 * @param value The value for the configuration object.
	 * @throws TransformerConfigurationException
	 * @throws ParserConfigurationException
	 */
	public <T>XmlConfigurationObject(String name, T value) throws TransformerConfigurationException, ParserConfigurationException {
		this(name, new ArrayList<ConfigurationProperty>(), new ArrayList<PersistXml>(), value);
	}
	
	/**
	 * 
	 * @param name The name of the configuration object.
	 * @param properties List of @see ConfigurationProperty assoicated with this configuration object.
	 * @param children List of @see PersistXml associated with this configuration object.
	 * @param value The value for the configuration object.
	 * @throws TransformerConfigurationException
	 * @throws ParserConfigurationException
	 */
	public <T>XmlConfigurationObject(String name, List<ConfigurationProperty> properties,
			List<PersistXml> children, T value) throws TransformerConfigurationException, ParserConfigurationException {
		this.name = name;
		this.configurationProperties = properties;
		this.children = children;
		this.value = value;
	}
	
	@Override
	public String name() {
		return name;
	}
	
	@Override
	public void name(String value) {
		name = value;
	}
	
	@Override
	public List<ConfigurationProperty> configurationProperties() {
		return configurationProperties;
	}
	
	@Override
	public void addConfigurationProperty(ConfigurationProperty property) {
		configurationProperties.add(property);
	}
	
	@Override
	public void addConfigurationProperties(List<ConfigurationProperty> properties) {
		configurationProperties.addAll(properties);
	}
	
	@Override
	public void removeConfigurationProperty(ConfigurationProperty property) {
		configurationProperties.remove(property);
	}
	
	@Override
	public void removeConfigurationProperties(List<ConfigurationProperty> properties) {
		configurationProperties.removeAll(properties);
	}
	
	@Override
	public void clearConfigurationProperties() {
		configurationProperties.clear();
	}
	
	@Override
	public List<PersistXml> children() {
		return children;
	}
	
	@Override
	public void addChild(PersistXml child) {
		children.add(child);
	}
	
	@Override
	public void addChildren(List<PersistXml> children) {
		this.children.addAll(children);
	}
	
	@Override
	public void removeChild(PersistXml child) {
		children.remove(child);
	}
	
	@Override
	public void removeChildren(List<PersistXml> children) {
		this.children.removeAll(children);
	}
	
	@Override
	public void clearChildren() {
		children.clear();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T value() {
		return (T)value;
	}
	
	@Override
	public <T> void value(T value) {
		this.value = value;
	}
	
	@Override
	public Element persist(XmlGenerator generator) {
		Element retVal = generator.createElement(name);
		
		for (ConfigurationProperty property : configurationProperties) {
			generator.addAttributeToElement(retVal, property.name(), property.value());
		}
		
		for (PersistXml child : children) {
			generator.addChildToElement(retVal, child.persist(generator));
		}
		
		if (value instanceof XmlConfigurationObject) {
			generator.addChildToElement(retVal, ((XmlConfigurationObject)value).persist(generator));
		} else if (value != null) {
			String valueType = getValueType();
			generator.addNodeToElement(retVal, value.toString());
			generator.addAttributeToElement(retVal, VALUE_TYPE, valueType);
		}
		return retVal;
	}

	@Override
	public void load(Element xmlObject) {
		configurationProperties.clear();
		children.clear();
		String valType = null;
		
		NamedNodeMap attributes = xmlObject.getAttributes();
		NodeList objects = xmlObject.getChildNodes();
		
		for (int i = 0; i < attributes.getLength(); i++) {
			Node configProperty = attributes.item(i);
			
			//This is a special case, in this case we are looking at a values type.
			if (configProperty.getNodeName().equals(VALUE_TYPE)) {
				valType = configProperty.getNodeValue();
				continue;
			}
			
			ConfigurationProperty property = new ConfigurationProperty(configProperty.getNodeName(), configProperty.getNodeValue());
			configurationProperties.add(property);
		}
		
		Element temp;
		for (int i = 0; i < objects.getLength(); i ++) {
			try {
				if (!(objects.item(i) instanceof Element)) continue;
				
				temp = (Element) objects.item(i);
				XmlConfigurationObject configObject = new XmlConfigurationObject(temp.getNodeName());
				configObject.load(temp);
				children.add(configObject);
			} catch (Exception e) {
				// TODO Figure out what to do here.
				e.printStackTrace();
			}
		}

		if (xmlObject.getTextContent() != null && valType != null) {
			value(getValueFromType(valType, xmlObject.getTextContent()));
		}
	}
	
	@Override
	public XmlConfigurationObject getChildObject(String name) {
		//TODO: rewrite this, I don't really like it.
		XmlConfigurationObject retVal = null;
		for (PersistXml child : children) {
			if (!(child instanceof XmlConfigurationObject)) continue;
			
			XmlConfigurationObject obj = (XmlConfigurationObject)child;
			if (obj.name().equals(name)) {
				retVal = obj;
				break;
			}
		}
		return retVal;
	}
	
	@Override
	public <T> T getChildValue(String name) {
		//TODO: rewrite this, I don't really like it.
		T retVal = null;
		for (PersistXml child : children) {
			if (!(child instanceof XmlConfigurationObject)) continue;
			
			XmlConfigurationObject obj = (XmlConfigurationObject)child;
			if (obj.name().equals(name)) {
				retVal = obj.value();
				break;
			}
		}
		return retVal;
	}
	
	private Object getValueFromType(String type, String value) {
		if (type.equals(ValueTypes.StringType)) {
			return value;
		}
		if (type.equals(ValueTypes.BooleanType)) {
			return Boolean.parseBoolean(value);
		}
		if (type.equals(ValueTypes.IntegerType)) {
			return Integer.parseInt(value);
		}
		if (type.equals(ValueTypes.FloatType)) {
			return Float.parseFloat(value);
		}
		if (type.equals(ValueTypes.DoubleType)) {
			return Double.parseDouble(value);
		}
		return value;
	}
	
	public String getValueType() {
		if (value instanceof String) {
			return ValueTypes.StringType;
		}
		if (value instanceof Boolean) {
			return ValueTypes.BooleanType;
		}
		if (value instanceof Integer) {
			return ValueTypes.IntegerType;
		}
		if (value instanceof Float) {
			return ValueTypes.FloatType;
		}
		if (value instanceof Double) {
			return ValueTypes.DoubleType;
		}
		return ValueTypes.ObjectType;
	}
}
