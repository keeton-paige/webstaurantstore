package webstaurantstore.pages;

import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResults {
	
	WebDriver driver;
	WebDriverWait wait; 
	SoftAssert softAssert = new SoftAssert();
	
	By ProductBox = By.xpath("//div[@data-testid='productBoxContainer']");
	
	//constructor for SearchResults
	public SearchResults(WebDriver driver) {
		this.driver=driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}
	
	//Method for checking all search results titles for a passed in keyword
	public void verifyResults(String keyword) {
		wait.until(ExpectedConditions.presenceOfElementLocated(ProductBox));
		
		//keeps track of the page number/item number to help with troubleshooting
		int itemsOnPage=1;
		int pages=0;
		
		//do-while loop to go through all the pages of results
		do {
			pages++;
	        List<WebElement> products = driver.findElements(By.xpath("//div[@data-testid='productBoxContainer']"));
	        
	        //reset the item number between each page
	        itemsOnPage=1;
	        
	        //for loop to go through all the items on the page
			for (WebElement product : products) {
				WebElement titleElement = product.findElement(By.xpath(".//span[@data-testid='itemDescription']"));
				String titleText = titleElement.getText();
				//line below was used to verify that i hit every item on the page
				//System.out.println(pages + "-" + itemsOnPage);
				
				//if title doesnt contain the keyword, print out an error message
				if (!titleText.toLowerCase().contains(keyword))
					System.out.println("ERROR: title does not contain "+keyword+" page-item : title = " +pages+"-"+itemsOnPage+" : "+titleText);
				//soft assert so the test continues
				softAssert.assertTrue(titleText.toLowerCase().contains(keyword));
				itemsOnPage++;
			}
	        
			//find the paging button that has the rounded right side, only the last button has a rounded right side
			//check if the aria-label says "go to page", if true we are not on the last page
			//last page aria-label starts with "current page"
			WebElement nextPageButton;
			try {
				nextPageButton = driver.findElement(By.cssSelector("#paging li.rounded-r-md a[aria-label *= 'go to page']"));
				nextPageButton.click();
			} catch (Exception e) {
				break;
			}
		} while (true);
	}
	
    public void addToCartLastItem() {
        // Locate the last item on the page
        List<WebElement> products = driver.findElements(By.xpath("//div[@data-testid='productBoxContainer']"));
        WebElement last = products.get(products.size()-1);

        // Add the last item to the cart
        WebElement addToCartButton = last.findElement(By.xpath("//input[@data-testid='itemAddCart']"));
        addToCartButton.click();
    }
}
		
		