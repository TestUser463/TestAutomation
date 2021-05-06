package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


public class AddCustomerPage{

public WebDriver ldriver;

	public AddCustomerPage(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(ldriver, this);
	}
	
	By lnkCustomers_menu=By.xpath("/html/body/div[3]/aside/div/div[4]/div/div/nav/ul/li[4]/a");
	
	By lnkCustomers_menuitem=By.xpath("//a[@href=\"/Admin/Customer/List\" and @class='nav-link']");
	By btnAddNew= By.xpath("//a[@href='/Admin/Customer/Create']");
	By txtEmail=By.xpath("//input[@id='Email']");
	By txtPassword=By.xpath("//input[@id='Password']");
	By txtFirstName=By.xpath("//input[@id='FirstName']");
	By txtLastName=By.xpath("//input[@id='LastName']");
	By rdMaleGender=By.xpath("//input[@id='Gender_Male']");
	By rdFemaleGender=By.xpath("//input[@id='Gender_Female']");
	By txtDOB=By.xpath("//input[@id='DateOfBirth']");
	By txtCompanyName=By.xpath("//input[@id='Company']");
	By cbTaxExempt=By.xpath("//input[@id='IsTaxExempt']");
	
	By txtNewsletter=By.xpath("//*[@id=\"customer-info\"]/div[2]/div[9]/div[2]/div/div[1]/div/div");
	By lstItemYourStoreName=By.xpath("//li[contains(text(),'Your store name')]");
	
	By txtCustomersRoles=By.xpath("//*[@id=\"customer-info\"]/div[2]/div[10]/div[2]/div/div[1]/div/div");
	By lstItemAdministrator=By.xpath("//li[contains(text(),'Administrators')]");
	By lstItemGuest=By.xpath("//li[contains(text(),'Guests')]");
	
	By txtAdminContent=By.xpath("//*[@id='AdminComment']");
	By btnSave=By.xpath("//button[@name='save']");
	
	//Actions Methods
	
	public String getPageTitle()
	{
		return ldriver.getTitle();
	}
	
	public void clickOnCustomersMenu() {
		ldriver.findElement(lnkCustomers_menu).click();
	}
	
	public void clickOnCustomersMenuItem() {
		ldriver.findElement(lnkCustomers_menuitem).click();
	}
	
	public void clickOnAddNew() {
		ldriver.findElement(btnAddNew).click();
	}
	
	public void setEmail(String email) {
		ldriver.findElement(txtEmail).sendKeys(email);
	}

	public void setPassword(String password) {
		ldriver.findElement(txtPassword).sendKeys(password);
	}
	
	public void setCustomerRoles(String role) throws InterruptedException 
	{
		if(!role.equals("Vendors"))
		{
		ldriver.findElement(By.xpath("//*[@id='SelectedCustomerRoleIds_taglist']/li/span[2]")).click();
		}
		ldriver.findElement(txtCustomersRoles).click();
		
		WebElement listitem;
		Thread.sleep(3000);
		
		if(role.equals("Administrators"))
		{
			listitem=ldriver.findElement(lstItemAdministrator);
		}
		else if(role.equals("Guests"))
		{
			listitem=ldriver.findElement(lstItemGuest);
		}
		
		else
		{
			listitem=ldriver.findElement(lstItemGuest);
		}
		listitem.click();
		
		//JavascriptExecutor js= (JavascriptExecutor) ldriver;
		//js.executeScript("arguments[0].click();", listitem);
		
	}
	
	public void setManagerOfVendor(String value)
	{
		ldriver.findElement(txtNewsletter).click();
		ldriver.findElement(lstItemYourStoreName).click();
		//Select drp=new Select(ldriver.findElement(txtNewsletter));
		//drp.selectByVisibleText(value);
	}
	
	public void setGender(String gender)
	{
		if(gender.equals("Male"))
		{
			ldriver.findElement(rdMaleGender).click();
		}
		else if(gender.equals("Female"))
		{
			ldriver.findElement(rdFemaleGender).click();
		}
		else
		{
			ldriver.findElement(rdMaleGender).click();
		}
	}
	
	public void setFirstName(String fname) {
		ldriver.findElement(txtFirstName).sendKeys(fname);
	}
	
	public void setLastName(String lname) {
		ldriver.findElement(txtLastName).sendKeys(lname);
	}
	
	public void setDOB(String dob) {
		ldriver.findElement(txtDOB).sendKeys(dob);
	}
	
	public void setCompanyName(String comname) {
		ldriver.findElement(txtCompanyName).sendKeys(comname);
	}
	
	public void setAdminContent(String content) {
		ldriver.findElement(txtAdminContent).sendKeys(content);
	}
	
	public void clickOnSave() {
		ldriver.findElement(btnSave).click();
	}
	
}
