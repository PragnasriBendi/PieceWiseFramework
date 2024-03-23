package tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.ConfirmationPage;
import pageObjects.ProductCatlogPage;

public class ErrorValidation extends BaseTest{
	
	@Test(groups= {"ErrorHandling"})
	public void loginErrorValidation() throws IOException {
		
		String productName = "ZARA COAT 3";
		ProductCatlogPage productCatalogue=land.loginApplication("pragna@yopmail.com", "Pragna@111");
		Assert.assertEquals("Incorrect email or password",land.getErrorMessage());
		
	}
	
	@Test
	public void productErrorValidation() throws IOException {
		
		String productName = "ZARA COAT 3";
		ProductCatlogPage productCatalogue=land.loginApplication("pragjjna@yopmail.com", "Pragna@111");
		
		List<WebElement>products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartpage=productCatalogue.goToCart();
		
		Boolean match=cartpage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
		
		

	}	


}
