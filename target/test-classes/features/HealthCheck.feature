@ui @HealthCheck

Feature: E-Commerce Website for Health Checkup
@Search
Scenario: User is able to open a Application and Able to perform a search Operation
#Given User open a Browser // # Is Show Comment
Given User Navigate to Landing page of an Application
When User search for a Product "Laptop"
Then Search result is Displayed "Laptop"
#Then close the application

@Search
Scenario: User is able to open a Application and Able to perform a  search Operation
#Given User open a Browser
Given User Navigate to Landing page of an Application
When User search for a Product "Mobile"
Then Search result is Displayed "Mobile"
#Then close the application

@productSearch
Scenario: User is click on the Product and Check the Product Details
#Given User open a Browser
Given User Navigate to Landing page of an Application
And User search for a Product "Laptop"
When User click on any product
Then product Description is Displayed On New Tab
#And Browser is Closed

@kidsToysSearch
Scenario: User is click on the Product and Check the Product Details
Given User Navigate to Landing page of an Application
And User search for a Product "kids toys for girls"
When User click on any product

@YourList
Scenario: Validate Your Lists Options on Landing page under Accounts & Lists Section
Given signin Accounts and lists options is avavible on landing page of application
When user mousehover on hello signin Accounts and lists
Then under Your Lists section following options are available
    | Create a Wish List 		|
    | Wish from Any Website |
    | Baby Wishlist					|
    | Discover Your Style		|
    | Explore Showroom			|

@YourAccountOptionsList
  Scenario: Validate Your Account Options on Landing page under Accounts & Lists Section
    Given signin Accounts and lists options is avavible on landing page of application
    When user mousehover on hello signin Accounts and lists
    Then under Your Account section following options are available
    | Your Account											|
		| Your Orders												|
		| Your Wish List										|
		| Your Recommendations							|
		| Your Prime Membership							|
    | Your Prime Video									|
		| Your Subscribe & Save Items				|
		| Memberships & Subscriptions				|
    | Your Free Amazon Business Account	|
		| Your Seller Account								|
		| Manage Your Content and Devices		|

@FooterLinksLists
	Scenario: Validate all footer links on landing page of the application
    Given user scroldown to the botton of the landing page of the application
    When user is able to see 4 main options categories
    And the categories having the option "Get to Know Us", "Connect with Us", "Make Money with Us" and "Let Us Help You"
    Then under Get to Know Us category below options are visible
    | About Us				|
    | Careers					|
    | Press Releases	|
    | Amazon Science	|   
		And under Connect with Us category below options are visible
		| Facebook 	|
		| Twitter		|
		| Instagram	| 
		And under Make Money with Us category below options are visible
		| Sell on Amazon 								|
		| Sell under Amazon Accelerator |
		| Protect and Build Your Brand 	|
		| Amazon Global Selling 				|
		| Become an Affiliate 					|
		| Fulfilment by Amazon 					|
		| Advertise Your Products				|
		| Amazon Pay on Merchants 			|
		And under Let Us Help You category below options are visible
		| COVID-19 and Amazon 			|
		| Your Account 							|
		| Returns Centre 						|
		| 100% Purchase Protection 	|
		| Amazon App Download 			|
		| Amazon Assistant Download |
		| Help 											|

	