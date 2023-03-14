package vit.automationTesting.stepdefs;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import vit.automationTesting.Core.WebDriverFactory;
import vit.automationTesting.pageObjects.LandingPageObject;
import vit.automationTesting.pageObjects.ProductDescPageObject;
import vit.automationTesting.pageObjects.ProductListingPageObject;

public class StepDefs {
	 Scenario scn;
	 WebDriver driver;
	 String base_url = "https://amazon.in";
	 int implicit_wait_timeout_in_sec = 20;
	 WebDriverWait webDriverWait;
	 
	 LandingPageObject landingPageObject;
	 ProductDescPageObject productDescPageObject;
	 ProductListingPageObject productListingPageObject;

	 private static final Logger logger = LogManager.getLogger(StepDefs.class);
	 	 
@Before

public void setUp(Scenario scn)
{
	this.scn=scn;
	
	String browserName = WebDriverFactory.getBrowserName();
	
	driver = WebDriverFactory.getWebDriverForbrowser(browserName);
	
	//driver = new ChromeDriver();
	webDriverWait = new WebDriverWait(driver,20);
	logger.info("Browser got Set");
	
//    driver.manage().window().maximize();
//    logger.info("Browser got Maximize");
//    driver.manage().timeouts().implicitlyWait(implicit_wait_timeout_in_sec, TimeUnit.SECONDS);
//    logger.info("Browser implicit timeout set to :> " +implicit_wait_timeout_in_sec);
//    scn.log("Browser Got Invoked");
    
    // Initialize Class objects
    
    landingPageObject = new LandingPageObject(driver, webDriverWait);
    productDescPageObject = new ProductDescPageObject(driver, webDriverWait);
    productListingPageObject = new ProductListingPageObject(driver, webDriverWait);
 
}
	
@After(order=1)

public void TearDown()
{
	WebDriverFactory.quitDriver();
	
	scn.log("Browser got Closed");
}

@After(order=2)

public void ScreenShotForFailure(Scenario scn)
{
	if(scn.isFailed())
	{
		TakesScreenshot screenShot = (TakesScreenshot)driver;
		
		byte[] data = screenShot.getScreenshotAs(OutputType.BYTES);
		
		scn.attach(data,"image/png","Failed Step Name:> " +scn.getName());	
	}
	else
	{
		scn.log("Test Case is passed , No screenshot Capture");
	}
 }
    @Given("User Navigate to Landing page of an Application")
	public void user_navigate_to_landing_page_of_an_application() 
	{
    	landingPageObject.validateUserIsOnLandingPage(base_url);
	    scn.log("User Navigate to the Landing page of Application");
	    
	}
	@When("User search for a Product {string}")
	public void user_search_for_a_product(String productName) 
	{
		landingPageObject.SearchProduct(productName);
        scn.log("User search For a Product");
	}
	@Then("Search result is Displayed {string}")
	public void search_result_is_displayed(String prodName)
	{
		productListingPageObject.validateSearchresult(prodName);
	    scn.log("Search result is Displayed");
	}
	
	@When("User click on any product")
	public void user_click_on_any_product() 
	{
		productDescPageObject.clickOnAnyProduct();
        scn.log("User click on any Product");
	}

	@Then("product Description is Displayed On New Tab")
	public void product_description_is_displayed_on_new_tab()
	{
		//As product description click will open new tab, we need to switch the driver to the new tab
        //If you do not switch, you can not access the new tab html elements
        //This is how you do it
		
		Set<String> handle = driver.getWindowHandles();
		
		Iterator<String> it = handle.iterator();
		
		String original = it.next(); // gives the parent window id
		String prodDescp  = it.next();// gives the Child window id
		
		driver.switchTo().window(prodDescp);	 // switch to product Descp
		
		//Now driver can access new driver window, but can not access the orignal tab
        //Check product title is displayed
		
        WebElement productTitle = driver.findElement(By.id("productTitle"));
        
        Assert.assertEquals("Product Title",true,productTitle.isDisplayed());

        WebElement addToCartButton = driver.findElement(By.xpath("//input[@id='add-to-cart-button']"));
        
        Assert.assertEquals("Product Title",true,addToCartButton.isDisplayed());

        //Switch back to the Original Window, however no other operation to be done
        
        driver.switchTo().window(original);
        scn.log("product description is displayed on New Tab");
	
	}
	
	// @YourList
	
	@Given("signin Accounts and lists options is avavible on landing page of application")
	public void signin_accounts_and_lists_options_is_avavible_on_landing_page_of_application() 
	{
		WebElement accListEle= driver.findElement(By.xpath("//a[@id='nav-link-accountList']"));
		webDriverWait.until(ExpectedConditions.invisibilityOf(accListEle));
		Assert.assertEquals(true,accListEle.isDisplayed());
	    
	}

	@When("user mousehover on hello signin Accounts and lists")
	public void user_mousehover_on_hello_signin_accounts_and_lists() 
	{
		WebElement accListEle= driver.findElement(By.xpath("//a[@id='nav-link-accountList']"));
		Actions action = new Actions(driver);
		
		action.moveToElement(accListEle).build().perform();
	}
	@Then("under Your Lists section following options are available")
	public void under_your_lists_section_following_options_are_available(List<String> ExpectedYourListOption)
	{
	   List<WebElement> yourListAccEle = driver.findElements(By.xpath("//div[text()='Your Lists']//parent::div[@id='nav-al-wishlist']//a//span"));
	   
	   for(int i=0;i<ExpectedYourListOption.size();i++)
	   {
		   if(ExpectedYourListOption.get(i).equals(yourListAccEle.get(i).getText()))
		   {
			   Assert.assertTrue(true);
		   }
		   else
		   {
			   Assert.fail();
				   
		   }
	}

	}
	@Then("under Your Account section following options are available")
	public void under_your_account_section_following_options_are_available(List<String> expectedYourAccountOptions ) 
	
	{
		List<WebElement> yourAccountActElement = driver.findElements(By.xpath("//div[text()='Your Account']//parent::div[@id='nav-al-your-account']//a/span"));

		for (int i = 0; i < expectedYourAccountOptions.size(); i++) 
		{
			if (expectedYourAccountOptions.get(i).equals(yourAccountActElement.get(i).getText()))
			{
				Assert.assertTrue(true);
			}
			else
			{
				Assert.fail();
			}

		}
	}  
	
	@Given("user scroldown to the botton of the landing page of the application")
	public void user_scroldown_to_the_botton_of_the_landing_page_of_the_application() throws InterruptedException
	{
		WebElement getToKnowUsElement = driver.findElement(By.xpath("//div[text()='Get to Know Us']"));

		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].scrollIntoView(true);", getToKnowUsElement);

		// This below line is just for demo, can be removed as well
		Thread.sleep(5000);
	}


	@When("user is able to see {int} main options categories")
	public void user_is_able_to_see_main_options_categories(Integer footerMainCatCount) 
	{
	    List<WebElement> footerMainCatListEle = driver.findElements(By.xpath("//div[@class='navFooterVerticalRow navAccessibility']/div[@class='navFooterLinkCol navAccessibility']/div[text()]"));
	    Assert.assertEquals("footerMainCatCount", footerMainCatListEle.size());
	}
	@When("the categories having the option {string}, {string}, {string} and {string}")
	public void the_categories_having_the_option_and(String categoryOneNameExp, String categoryTwoNameExp, String categoryThreeNameExp, String categoryFourNameExp)
	{
		List<WebElement> footerMainCatListEle = driver.findElements(By.xpath("//div[@class='navFooterVerticalRow navAccessibility']/div[@class='navFooterLinkCol navAccessibility']/div[text()]"));
		Assert.assertEquals(categoryOneNameExp, footerMainCatListEle.get(0).getText());
		Assert.assertEquals(categoryTwoNameExp, footerMainCatListEle.get(1).getText());
		Assert.assertEquals(categoryThreeNameExp, footerMainCatListEle.get(2).getText());
		Assert.assertEquals(categoryFourNameExp, footerMainCatListEle.get(3).getText());

	}
	@Then("under Get to Know Us category below options are visible")
	public void under_get_to_know_us_category_below_options_are_visible(List<String> expectedGettoKnowUsOptions)
	{
		List<WebElement> GettoKnowUsOptionsActElement = driver.findElements(By.xpath("//div[text()='Get to Know Us']//parent::div//ul/li/a[text()]"));

		for (int i = 0; i < expectedGettoKnowUsOptions.size(); i++) {
			if (expectedGettoKnowUsOptions.get(i).equals(GettoKnowUsOptionsActElement.get(i).getText())) {
				Assert.assertTrue(true);
			}
			else
			{
				Assert.fail();
			}

		}
	}
	@Then("under Connect with Us category below options are visible")
	public void under_connect_with_us_category_below_options_are_visible(List<String> expectedConnectWithUsOptions)
	{
		List<WebElement> ConnectWithUsOptionsActElement = driver.findElements(By.xpath("//div[text()='Connect with Us']//parent::div//ul/li/a[text()]"));

		for (int i = 0; i < expectedConnectWithUsOptions.size(); i++) 
		{
			if (expectedConnectWithUsOptions.get(i).equals(ConnectWithUsOptionsActElement.get(i).getText()))
			{
				Assert.assertTrue(true);
			}
			else
			{
				Assert.fail();
			}

		}
	}

	@Then("under Make Money with Us category below options are visible")
	public void under_make_money_with_us_category_below_options_are_visible(List<String> expectedMakeMoneyWithUsOptions )
	{
		List<WebElement> MakeMoneyWithUsOptionsActElement = driver.findElements(By.xpath("//div[text()='Make Money with Us']//parent::div//ul/li/a[text()]"));

		for (int i = 0; i < expectedMakeMoneyWithUsOptions.size(); i++) 
		{
			if (expectedMakeMoneyWithUsOptions.get(i).equals(MakeMoneyWithUsOptionsActElement.get(i).getText()))
			{
				Assert.assertTrue(true);
			}
			else
			{
				Assert.fail();
			}

		}
	}
	@Then("under Let Us Help You category below options are visible")
	public void under_let_us_help_you_category_below_options_are_visible(List<String>expectedLetUsHelpYouOptions ) 
	{
		List<WebElement> LetUsHelpYouOptionsActElement = driver.findElements(By.xpath("//div[text()='Let Us Help You']//parent::div//ul/li/a[text()]"));

		for (int i = 0; i < expectedLetUsHelpYouOptions.size(); i++) {
			if (expectedLetUsHelpYouOptions.get(i).equals(LetUsHelpYouOptionsActElement.get(i).getText())) {
				Assert.assertTrue(true);
			}
			else
			{
				Assert.fail();
			}

		}
	}
}
