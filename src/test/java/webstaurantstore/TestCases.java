package webstaurantstore;

import java.io.IOException;

import org.testng.annotations.Test;

import webstaurantstore.pages.Cart;
import webstaurantstore.pages.Homepage;
import webstaurantstore.pages.SearchResults;
import webstaurantstore.resources.Browser;

public class TestCases extends Browser{

	@Test
	public void Test() throws IOException, InterruptedException {
		
		//Search for the product
		Homepage home = new Homepage(driver);
		home.searchStore("stainless work table");
		
		//Verify the results have "table" in the title
		SearchResults results = new SearchResults(driver);
		results.verifyResults("table");
		
		//Add the last result of the last page to the cart
		results.addToCartLastItem();
		
		//Open the cart page and empty all items from the cart
		Cart cart = new Cart(driver);
		cart.openCart();
		cart.emptyCart();
	}
}
