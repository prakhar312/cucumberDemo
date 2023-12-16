@tag
Feature: Purchase the order from Ecommerce site 

Background: 
Given: User try to login the website

Scenario Outline: Positive test of login page


Given user is on the login page
When user enters the username<username> and password<password>
And click on submit button
Then user is navigated to the next page

Examples: 
|username          |password        |
|rahul312@gmail.com|Alohomora@312   |

