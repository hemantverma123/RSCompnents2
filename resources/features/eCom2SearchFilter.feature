#'Create a page object model framework which has 5 automation tests written in a BDD format which demonstrate 
#key journeys from the homepage to the purchase page for the RS Website - http://uk.rs-online.com/web/
#The breakdown of the areas to test are:
#2x – End to End Test – Homepage to checkout (do not purchase) 3x – Search filter results page Test

@SearchFilterTest
Feature: Search filter results page Test 
	As a user I should be able to search proucts

Background: 
Given initial setup completed for SearchFilter
And get the url from config file

Scenario Outline: User searches as Guest
Given user clicks on Home button for search 
When user search "<Product>"
And user clicks search button
Then user should see "<Result>" 

Examples:
|product			|Result												|
|no product exist	|We couldn't find any results for 'no product exist'|
|solder paste		|Your search returned 38 products in 2 categories	|
|""					|You have encountered an Error...					|