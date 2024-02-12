package webstaurantstore.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Cart {
	
	WebDriver driver;
	
	//constructor for cart
	public Cart(WebDriver driver) {
		this.driver=driver;
	}
	
	//opens the cart, wait 15 seconds to execute to make sure the newly added to cart popup is gone
	public void openCart() throws InterruptedException {
    	Thread.sleep(15000);
    	WebElement cartButton = driver.findElement(By.xpath("//a[@data-testid='cart-button']"));
    	cartButton.click();
    }
    
	//empty the cart via the empty cart button
    public void emptyCart() {
    	WebElement cartButton = driver.findElement(By.xpath("//button[normalize-space()='Empty Cart']"));
    	cartButton.click();
    	
    	//find the confirmation modal and click empty
    	WebElement confirmModal = driver.findElement(By.cssSelector("footer[data-testid='modal-footer']"));
    	WebElement cartButtonConfirm = confirmModal.findElement(By.xpath("button[contains(text(),'Empty')]"));
    	cartButtonConfirm.click();
    }
}
