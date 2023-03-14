package vit.automationTesting.pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LandingPageObject {
	
	 private static final Logger logger = LogManager.getLogger(LandingPageObject.class);
	 
	 //Section1:  Declare a driver object
	 
	 WebDriver driver;
	 WebDriverWait webDriverWait;
	 
	// Section 2: Paratmerize the constuctor
	 
	 public LandingPageObject(WebDriver driver, WebDriverWait webDriverWait )
	 {
		 this.driver =driver;
		 this.webDriverWait=webDriverWait;
	 }
	 
	//Section 3 : Define the locators
	 
	 private By SearchBoxElement = By.id("twotabsearchtextbox");

     private By SearchButtonEle = By.xpath("//input[@value='Go']"); // If I Erase one slash from input it show Error and Then Take ScreenShot of Entire Screen
     
 	//Section 4 : Write Business Methods (methods to be exposed) agent
     
     public void SearchProduct(String productName)
     {
    	 webDriverWait.until(ExpectedConditions.elementToBeClickable(SearchBoxElement));
    	 logger.info("Waiting for Element:> SearchBoxElement to be clickable");
    	 driver.findElement(SearchBoxElement).sendKeys(productName);
    	 logger.info("Sending Key into WebElement:> SearchBoxElement");
    	 
    	 driver.findElement(SearchButtonEle).click();
    	 logger.info("Clickimg on the Search Button");
    	 	 
     }
     
     public void validateUserIsOnLandingPage(String base_url)
     {
    	 driver.get(base_url);
    	 logger.info("Browser got invoke the base URL:> " +base_url);
    	 
         String expected = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
         String actual = driver.getTitle();
         Assert.assertEquals("Page Title validation",expected,actual);
         logger.info("Assertion of page Validation title was passed with expectes as:> " +expected+ "As Actual was:> " +actual);
    	 
     }
	
}
