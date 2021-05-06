Feature: Customers

Background: Below are the common steps for each scenario
	Given  User Launch Chrome browser
	When User opens URL "http://admin-demo.nopcommerce.com/login"
	And User enters Email as "admin@yourstore.com" and Password as "admin"
	And Click on Login
	Then User can view Dashboard

@regression
Scenario: Add a new Customer
	When User click on customers Menu
	And click on customers Menu Item
	And Click on Add new button
	Then User can view Add new customer page
	When User enter customer info
	And click on Save button
	Then user can view confirmation message "The new customer has been added successfully."
	And close browser

@sanity
Scenario: Search Customer by EmailID
	When User click on customers Menu
	And click on customers Menu Item
	And Enter Customer Email
	When Click on Search Button
	Then User should found Email in the search table
	And close browser
	