@tag
Feature: Validating Place API's

  @AddPlace
  Scenario Outline: Verify if place is being Successfully added using addPlaceAPI
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When User calls "AddPlaceAPI" with "POST" http request
    Then API call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And Verify place_Id created maps to "<name>" using "getPlaceAPI"
    
    
    
  Examples:
  	|name|language|address|
  	|AAhouse|English|World Center| 
  	|BBhouse|Spanish|Half Center| 

@DeletePlace
Scenario: Verify if the place created is deleted Successfully using deletePlaceAPI
		Given Delete Place Payload
		When User calls "DeletePlaceAPI" with "DELETE" http request
		Then API call is success with status code 200
    And "status" in response body is "OK"





  #@tag2
  #Scenario Outline: Title of your scenario outline
    #Given I want to write a step with <name>
    #When I check for the <value> in step
    #Then I verify the <status> in step
#
    #Examples: 
      #| name  | value | status  |
      #| name1 |     5 | success |
      #| name2 |     7 | Fail    |