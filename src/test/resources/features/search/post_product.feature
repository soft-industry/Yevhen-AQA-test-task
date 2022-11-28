Feature: Verify search for the product endpoint

### Please use endpoint GET https://waarkoop-server.herokuapp.com/api/v1/search/test/{product} for getting the products.
### Available products: "apple", "mango", "tofu", "water"
### Prepare Positive and negative scenarios

  Scenario Outline: Positive tests for all available products
    When user calls endpoint "<requestUrl>" using "GET" http method
    Then response status code is 200
    And response body contains "title" field which contains "<itemTitle>" value

    Examples:
      | requestUrl        | itemTitle |
      | search/test/apple | apple     |
      | search/test/mango | mango     |
      | search/test/tofu  | tofu      |
      | search/test/water | water     |

  Scenario: Negative test for incorrect product value
    When user calls endpoint "search/test/cars" using "GET" http method
    Then response status code is 404
    And response body contains "error" field with true value
    And response body contains "message" field with "Not found" value
    And response body contains "requested_item" field with "cars" value

  Scenario: Negative test for incorrect url
    When user calls endpoint "search/test1/apple" using "GET" http method
    Then response status code is 404
    And response body contains "Not Found" error message

  Scenario Outline: Negative tests for incorrect http method
    When user calls endpoint "search/test/apple" using "<httpMethod>" http method
    Then response status code is 405
    And response body contains "Method Not Allowed" error message

    Examples:
      | httpMethod |
      | POST       |
      | PUT        |
      | DELETE     |
      | OPTIONS    |

