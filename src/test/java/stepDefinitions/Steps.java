package stepDefinitions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class Steps extends BaseClass {

	@Before
	public void setup() throws IOException
	{
		logger=Logger.getLogger("nopCommerce");
		PropertyConfigurator.configure("log4j.properties");
		
		configProp=new Properties();
		FileInputStream configPropfile=new FileInputStream("config.properties");
		configProp.load(configPropfile);
		
		String br=configProp.getProperty("browser");
		if(br.equals("chrome"))
		{
			 System.setProperty("webdriver.chrome.driver", configProp.getProperty("chromepath"));
			 driver=new ChromeDriver();
		}
		else if(br.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", configProp.getProperty("firefoxpath"));
		    driver=new ChromeDriver();
		}
			
	}
	
	@Given("User Launch Chrome browser")
	public void user_launch_chrome_browser() {
		
	    logger.info("***********Launching Browser********");
	    lp=new LoginPage(driver);
	}

	@When("User opens URL {string}")
	public void user_opens_url(String url) {
		logger.info("***********Opening URL***********");
		driver.get(url);
		driver.manage().window().maximize();
	}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_email_as_and_password_as(String email, String password) {
		logger.info("***********Providing login details********");
		lp.setUserName(email);
		lp.setPassword(password);
	}

	@When("Click on Login")
	public void click_on_login() throws InterruptedException {
		logger.info("***********Started login ********");
	   lp.clickLogin();
	   Thread.sleep(3000);
	}

	@Then("Page Title should be {string}")
	public void page_title_should_be(String title) throws InterruptedException {
	    if(driver.getPageSource().contains("Login was unsuccessful.")) {
	    	driver.close();
	    	logger.info("***********Login Passed********");
	    	Assert.assertTrue(false);
	    } else {
	    	logger.info("***********Login Failed********");
	    	Assert.assertEquals(title, driver.getTitle());
	    }
	    Thread.sleep(3000);
	}

	@When("User click on Log out link")
	public void user_click_on_log_out_link() throws InterruptedException {
		logger.info("***********Click on logout link********");
	   lp.clickLogout();
	   Thread.sleep(10000);
	}

	@Then("close browser")
	public void close_browser() {
		logger.info("***********Closing Browser********");
	   driver.quit();
	}

	//customers feature started
	@Then("User can view Dashboard")
	public void user_can_view_dashboard() {
	    
		addCust=new AddCustomerPage(driver);
		Assert.assertEquals("Dashboard / nopCommerce administration", addCust.getPageTitle());
	}
	@When("User click on customers Menu")
	public void user_click_on_customers_menu() throws InterruptedException {
		Thread.sleep(3000);
	    addCust.clickOnCustomersMenu(); 
	}
	@When("click on customers Menu Item")
	public void click_on_customers_menu_item() throws InterruptedException {
		Thread.sleep(2000);
	    addCust.clickOnCustomersMenuItem();
	}
	@When("Click on Add new button")
	public void click_on_add_new_button() throws InterruptedException {
		addCust.clickOnAddNew();
		Thread.sleep(3000);
	    
	}
	@Then("User can view Add new customer page")
	public void user_can_view_add_new_customer_page() {
		
		Assert.assertEquals("Add a new customer / nopCommerce administration", addCust.getPageTitle());
	   
	}
	@When("User enter customer info")
	public void user_enter_customer_info() throws InterruptedException {
		logger.info("***********Adding a new customer********");
		logger.info("***********Providing customer details********");
	   String email=randomestring()+ "@gmail.com";
	   addCust.setEmail(email);
	   addCust.setPassword("test123");
	   addCust.setCustomerRoles("Guests");
	   Thread.sleep(3000);
	   addCust.setManagerOfVendor("Your store name");
	   addCust.setGender("Male");
	   addCust.setFirstName("XYZ");
	   addCust.setLastName("ABC");
	   addCust.setDOB("10/10/1985");
	   addCust.setCompanyName("QA");
	   addCust.setAdminContent("This is for testing.....");
	}
	@When("click on Save button")
	public void click_on_save_button() throws InterruptedException {
		logger.info("***********Saving customer data********");
	    addCust.clickOnSave();
	    Thread.sleep(2000);
	}
	@Then("user can view confirmation message {string}")
	public void user_can_view_confirmation_message(String msg) {
	   Assert.assertTrue(driver.findElement(By.tagName("body")).getText()
			 .contains("The new customer has been added successfully"));
	}
	
	SearchCustomerPage searchCust;
	@When("Enter Customer Email")
	public void enter_customer_email() {
	    searchCust = new SearchCustomerPage(driver);
	    searchCust.setEmail("victoria_victoria@nopCommerce.com");
	    
	}
	@When("Click on Search Button")
	public void click_on_search_button() throws InterruptedException {
		logger.info("***********Searching customer********");
		searchCust.clickSearch();
		Thread.sleep(3000);
	   
	}
	@Then("User should found Email in the search table")
	public void user_should_found_email_in_the_search_table() {
		boolean status=searchCust.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
		Assert.assertEquals(true, status);
	}
	

}



