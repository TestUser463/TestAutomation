@TSTAUTO-607
Feature: Default

	
	@TSTAUTO-609
	Scenario: Successful Login with Valid Credentials using GIT
		Given  User Launch Chrome browser
			When User opens URL "http://admin-demo.nopcommerce.com/login"
			And User enters Email as "admin@yourstore.com" and Password as "admin"
			And Click on Login
			Then Page Title should be "Dashboard / nopCommerce administration"
			When User click on Log out link
			Then Page Title should be "Your store. Login"
			And close browser