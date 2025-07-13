Feature: Users API scenarios

@UserAPI @runThis
Scenario Outline: POST - Create user
  Given login endpoint is "/api/users"
  And user loads create user request body from "CreateUser.json"
  When a POST request for creating user is sent
  Then reponse body is stored in "CreateUserResponse".json file
  And the response status code should be 201
  And the response body should contain new generated id
  
@UserAPI
Scenario Outline: GET - Single user
  Given login endpoint is "/api/users/<id>"
  When a GET request is sent
  Then reponse body is stored in Response.json file
  And the response status code should be 200
  And the response body should contain user with id <id>
  And the response body should contain the following:
    | email                  | first_name   | last_name |
    | janet.weaver@reqres.in | Janet        | Weaver    |
Examples:
| id |
| 2  |

@UserAPI @runThis
Scenario Outline: Update - Existing user
  Given login endpoint is "/api/users/<id>"
  And user loads create user request body from "CreateUser.json"
  And update the user request body as:
	  |   name  |    job     |
	  | Chihiro |  Swordsman |
  When a PUT request for updating user is sent
  Then reponse body is stored in "UpdateUserResponse".json file
  And the response status code should be 200
Examples:
| id  |
| 633 |

@UserAPI @runThis
Scenario Outline: DELETE - Existing user
  Given login endpoint is "/api/users/<id>"
  When a DELETE request for deleting user is sent
  Then the response status code should be 204
Examples:
| id  |
| 633 |

@UserAPI
Scenario Outline: GET - List of users
  Given login endpoint is "/api/users?page=<pageId>"
  When a GET request is sent
  Then the response status code should be 200
  And the response body should contain list of users
  And the response body should contain users from page <pageId>
Examples:
| pageId |
|   3    |