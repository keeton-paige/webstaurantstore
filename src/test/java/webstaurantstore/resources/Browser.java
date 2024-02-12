package webstaurantstore.resources;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Browser {
	
	public static WebDriver driver;
	public static Properties prop = new Properties();
	public static FileReader fr;
	
	//Executes before each test
	@BeforeTest
	public void setUp() throws IOException {
		
		FileReader fr = new FileReader("configs.properties");
		prop.load(fr);
		
		//get the values from the config.properties
		String browserType = prop.getProperty("browser");
		String myUrl = prop.getProperty("url");
		
		//verify browser and url from config.properties is not null
		if (browserType == null || myUrl == null) {
			return;
		}
		//switch for launching different browsers
		switch (browserType.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.get(myUrl);
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.get(myUrl);
			break;
		//defaulting to chrome browser
		default:
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.get(myUrl);
		}
	}
	
	//cleanup after tests
	@AfterTest
	public void tearDown() {
		driver.close();
		System.out.println("Teardown");
	}
}
