package webstaurantstore.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Homepage {
	
	WebDriver driver;
	WebDriverWait wait; 
	
	//constructor for homepage
	public Homepage(WebDriver driver) {
		this.driver=driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	//Locator for search bar
	By SearchBar = By.xpath("//input[@data-testid='searchval']");
	
	//Method for searching for a passed in term and hitting enter
	public void searchStore(String searchTerm) {
		wait.until(ExpectedConditions.presenceOfElementLocated(SearchBar));
		driver.findElement(SearchBar).sendKeys(searchTerm + "\n");
	}
}

