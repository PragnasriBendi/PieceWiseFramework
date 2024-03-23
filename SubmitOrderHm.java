package tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.ConfirmationPage;
import pageObjects.LandingPage;
import pageObjects.OrderPage;
import pageObjects.ProductCatlogPage;
public class SubmitOrderHm extends BaseTest {
	String productName = "ZARA COAT 3";

	@Test(dataProvider="getData", groups= {"Purchase"})
	public void sumbitOrder(HashMap<String,String>input) throws IOException {	
		ProductCatlogPage productCatalogue=land.loginApplication(input.get("email"),
				input.get("password"));
		
		List<WebElement>products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartpage=productCatalogue.goToCart();
			
		Boolean match=cartpage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);	
		CheckoutPage checkoutpage=cartpage.goToCheckout();
		checkoutpage.selectCountry("India");
		ConfirmationPage confirmationpage=checkoutpage.submitOrder();	
		String confirmMessage=confirmationpage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}	
	
	@Test(dependsOnMethods= {"sumbitOrder"})
	public void orderHistoryTest() {
		ProductCatlogPage productCatalog=land.loginApplication("pragna@yopmail.com", "Pragna@111");
		OrderPage orderPage=productCatalog.goToOrdersPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
	}
	
	/*@DataProvider
	public Object[][] getData() {
		return new Object[][]  {{"pragna@yopmail.com","Pragna@111","ZARA COAT 3"}
		,{"pragna@yopmail.com","Pragna@111","ADIDAS ORIGINAL"}};
	}*/
	
	@DataProvider
	public Object[][] getData() {
		
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("email", "pragna@yopmail.com");
		map.put("password", "Pragna@111");
		map.put("product", "ZARA COAT 3");
		
		HashMap<String,String> map1 = new HashMap<String,String>();
		map1.put("email", "pragna@yopmail.com");
		map1.put("password", "Pragna@111");
		map1.put("product", "ADIDAS ORIGINAL");
		
		return new Object[][]  {{map}
		,{map1}};
	}

}
