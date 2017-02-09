package com.joormessage.propertyParser;

import java.io.IOException;
import java.util.Properties;

import com.joormessage.general.CONF;

/**
 * Class to load the property files. 
 * @author fcurtiaga
 * This class implements the Singleton design pattern to make sure that only one instance will be created.
 */
public class PropertyAccess {

	private static PropertyAccess INSTANCE = null;
	private static Properties properties = null;

	private PropertyAccess() {
		try {
			properties = new Properties();
			properties.load(PropertyAccess.class.getResourceAsStream(CONF.PROPERTY_FILE_PATH));
		} catch (NullPointerException e) {
			System.out.println("File could not be found.");
			e.printStackTrace();
		}
		catch (Exception e) {
			System.out.println("File could not be found.");
			e.printStackTrace();
		}
	 
	}	
		private static PropertyAccess getInstance() {
			if (INSTANCE == null) {
				INSTANCE = new PropertyAccess();
			}
			return INSTANCE;
		}
		
		public static Properties getProperties() {
			getInstance();
			return properties;
		}
		
		

	}

