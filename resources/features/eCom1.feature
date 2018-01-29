#'Create a page object model framework which has 5 automation tests written in a BDD format which demonstrate 
#key journeys from the homepage to the purchase page for the RS Website - http://uk.rs-online.com/web/
#The breakdown of the areas to test are:
#2x – End to End Test – Homepage to checkout (do not purchase) 3x – Search filter results page Test

@E2ETest1
Feature: E2E Scenarios 
	As a user I should be able to buy any product on the website

Background: 
Given initial setup completed
And get the url from config file

Scenario: User buys as Guest
Given initial setup completed
And get the url from config file
And user clicks on Home button 
And user selects New Products
And user selects product "Solder Paste" to buy
And user selects product category "Solder Pastes" 
And user adds selected product to the basket
And user click basket button
Then "My basket" page is displayed with the product "Solder Paste" 
Then take the screenshot as "Guest"

Scenario: User buys as resgisterd user
When user login with user "testuser1" and password "a1234567" 
Then verify login success
When user selects New Products
And user selects product "Solder Paste" to buy
And user selects product category "Solder Pastes" 
And user adds selected product to the basket
And user click basket button
Then "My basket" page is displayed with the product "Solder Paste" 
Then take the screenshot as "registerdUser"