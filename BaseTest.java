package tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import pageObjects.LandingPage;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage land;
	
	public WebDriver initializeDriver() throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//GlobalData.properties");
		prop.load(fis);
		String browserName=prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome")) {
		
		driver = new ChromeDriver();
		
		}else if(browserName.equalsIgnoreCase("firefox")) {
			//firefox
		}else if(browserName.equalsIgnoreCase("edge")) {
			//edge
			
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
		
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		LandingPage land = new LandingPage(driver);
		land.goTo();
        return land;
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.close();
	}

}
