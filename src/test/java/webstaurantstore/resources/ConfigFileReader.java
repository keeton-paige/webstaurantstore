package webstaurantstore.resources;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

	public static void main(String[] args) throws IOException {
		
		FileReader fr = new FileReader("configs.properties");
		Properties prop = new Properties();
		prop.load(fr);
		
	}
}
