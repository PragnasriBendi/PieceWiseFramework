package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import reusable.AbstractCompo;

public class LandingPage extends AbstractCompo {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver){
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
		
    @FindBy(id="userEmail")
    WebElement userEmail;
    
    @FindBy(id="userPassword")
    WebElement passwordEle;
    
    @FindBy(name="login")
    WebElement submit;
    
    @FindBy(css="[class*='flyInOut']")
    WebElement errorMessage;
    
    
    public ProductCatlogPage loginApplication(String email, String password) {
    	userEmail.sendKeys(email);
    	passwordEle.sendKeys(password);
    	submit.click();
    	ProductCatlogPage productCatalogue = new ProductCatlogPage(driver);
    	return productCatalogue;
    }
    
    public String getErrorMessage() {
    	waitForWebElementToAppear(errorMessage);
    	return errorMessage.getText();
    }
    
    public void goTo() {
    	driver.get("https://rahulshettyacademy.com/client");
    }
    
    
    
    
    
    
}
