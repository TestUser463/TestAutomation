package stepDefinitions;

import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;

public class BaseClass {

	public WebDriver driver;
	public LoginPage lp;
	public AddCustomerPage addCust;
	public Logger logger;
	public Properties configProp;
	
	//created for generating unique email id
	public static String randomestring() {
		String generatedString1=RandomStringUtils.randomAlphabetic(5);
		return(generatedString1);
	}
}
