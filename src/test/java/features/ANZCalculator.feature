#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Borrowing Calculator


  @Sanity
  Scenario Outline: Validate borrowing estimate
    Given User is able to open the production URL with "<Keyword>"
		When User provides expense details
		Then The calculated amount should match with the expected amount
    Examples: 
      | Keyword  |
      | TC_001 |

  @Sanity
  Scenario Outline: Validate start over button clears the form
    Given User is able to open the production URL with "<Keyword>"
		When User provides expense details
		Then After clicking on Start Over button the form got cleared
    Examples: 
      | Keyword  |
      | TC_002 |
      
   @Sanity
  Scenario Outline: Validate Error message
    Given User is able to open the production URL with "<Keyword>"
		When User provides only living expense details
		Then After clicking on Work out how much I could borrow button error message returns
    Examples: 
      | Keyword  |
      | TC_003 |