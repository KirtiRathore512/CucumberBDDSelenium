package vit.automationTesting.pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductListingPageObject {
	
 private static final Logger logger = LogManager.getLogger(ProductListingPageObject.class);
	 
	 //Section1:  Declare a driver object
	 
	 WebDriver driver;
	 WebDriverWait webDriverWait;
	 
	// Section 2: Paratmerize the constuctor
	 
	 public ProductListingPageObject(WebDriver driver, WebDriverWait webDriverWait )
	 {
		 this.driver =driver;
		 this.webDriverWait=webDriverWait;
	 }
	// Section 3 : Define the locators
		// private By searchBoxElement = By.id("twotabsearchtextbox");
	 
	// Section 4 : Write Business Methods (methods to be exposed) agent
	 
	 public void validateSearchresult(String prodName)
	 {
		 logger.info("Waiting for page title to contain:> " +prodName);
		
		 webDriverWait.until(ExpectedConditions.titleIs("Amazon.in : "+prodName+""));
		 
		//Assertion for Page Title
		 
		 Assert.assertEquals("Page Title validation","Amazon.in : "+prodName+"", driver.getTitle());
		 logger.info("Assertion Passed for validation of Search Result with product name as -> " + prodName);	 
	 }
	
}
