package client;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author pramothinidk
 *
 */
public class CarModelOptionsIO {
	/**
	 * Creates properties object from file, using the load method
	 * @param filename
	 * @return
	 */
public Properties createPropertiesObject(String filename){
	FileInputStream inputStream;
	Properties properties = new Properties();
	try {
		inputStream = new FileInputStream(filename);
		properties.load(inputStream);
		inputStream.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return properties;
}
}
