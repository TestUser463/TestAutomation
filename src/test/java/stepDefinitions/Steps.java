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

import pageObjects.LoginPage;


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


}



