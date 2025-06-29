Feature: Users API scenarios

@runThis
Scenario Outline: GET - Single user
  Given login endpoint is "/api/users/<id>"
  When a GET request is sent
  Then the response status code should be 200
  And the response body should contain user with id <id>
  And the response body should contain the following:
	  |        email           |  first_name | last_name |
	  | janet.weaver@reqres.in |    Janet    |  Weaver   |
Examples:
| id |
| 2  |


Scenario Outline: GET - List of users
  Given login endpoint is "/api/users?page=<pageId>"
  When a GET request is sent
  Then the response status code should be 200
  And the response body should contain list of users
  And the response body should contain users from page <pageId>
Examples:
| pageId |
|   3    |


Scenario Outline: POST - Create user
  Given login endpoint is "/api/users"
  And the request body is:
	  |   name  |     job     |
	  | Chihiro |  Swordsmith |
  When a POST request is sent
  Then the response status code should be 201
  And the response body should contain new generated id


Scenario Outline: Update - Existing user
  Given login endpoint is "/api/users/<id>"
  And the request body is:
	  |   name  |    job     |
	  | Chihiro |  Swordsman |
  When a PUT request is sent
  Then the response status code should be 200
Examples:
| id |
| 2  |


Scenario Outline: DELETE - Existing user
  Given login endpoint is "/api/users/<id>"
  When a DELETE request is sent
  Then the response status code should be 204
Examples:
| id |
| 2  |

