Feature: GoREST web-services testing 

Scenario: Create a User on GoRest application 
	Given Perform a Post call for given test data 
	When User is created successfully then print response body 
	Then Validate the status code as "200" 
	And Validate name and status from response body