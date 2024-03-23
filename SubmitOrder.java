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
public class SubmitOrder extends BaseTest {
	String productName = "ZARA COAT 3";

	@Test
	public void sumbitOrder() throws IOException {	
		ProductCatlogPage productCatalogue=land.loginApplication("pragna@yopmail.com", "Pragna@111");
					
		List<WebElement>products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartpage=productCatalogue.goToCart();
			
		Boolean match=cartpage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);	
		CheckoutPage checkoutpage=cartpage.goToCheckout();
		checkoutpage.selectCountry("India");
		ConfirmationPage confirmationpage=checkoutpage.submitOrder();	
		String confirmMessage=confirmationpage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}	
	
	@Test
	public void orderHistoryTest() {
		ProductCatlogPage productCatalog=land.loginApplication("pragna@yopmail.com", "Pragna@111");
		OrderPage orderPage=productCatalog.goToOrdersPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
	}
	
	
	
	

}
