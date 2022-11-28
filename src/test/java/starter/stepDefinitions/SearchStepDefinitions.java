package starter.stepDefinitions;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import starter.models.ErrorResponse;
import starter.models.ItemsNotFoundResponse;
import starter.models.SearchResponse;
import starter.services.FoodService;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;


public class SearchStepDefinitions {

    @Steps
    public FoodService foodService;

    @ParameterType(value = "true|True|TRUE|false|False|FALSE")
    public boolean aBoolean(String stringBooleanValue) {
        return Boolean.parseBoolean(stringBooleanValue);
    }

    @When("user calls endpoint {string} using {string} http method")
    public void callEndpointByHTTPMethodAndURL(String requestUrl, String httpMethod) {
        foodService.sendRequest(requestUrl, httpMethod);
    }

    @Then("response status code is {int}")
    public void checkResponseStatusCode(int expectedStatusCode) {
        assertThat(foodService.getStatusCodeOfCurrentResponse()).isEqualTo(expectedStatusCode);
    }

    @Then("response body contains \"title\" field which contains {string} value")
    public void checkResponseBodyFieldValue(String expectedValue) {
        List<SearchResponse> searchResponseItems = foodService.getCurrentResponseAsSearchResponseObject();
        List<SearchResponse> invalidResponseItems = new ArrayList<>();

        searchResponseItems.forEach(responseItem -> {
            if (!responseItem.getTitle().toLowerCase().contains(expectedValue)) {
                invalidResponseItems.add(responseItem);
            }
        });

        assertThat(searchResponseItems.size())
                .as("Response should contains at least one item")
                .isGreaterThan(0);
        assertThat(invalidResponseItems.size())
                .as("Response should not contain items without search keyword in title")
                .isEqualTo(0);
    }

    @Then("response body contains \"error\" field with {aBoolean} value")
    public void responseBodyContainsErrorFieldWithValue(boolean expectedBooleanValue) {
        ItemsNotFoundResponse itemsNotFoundResponse = foodService.getCurrentResponseAsItemsNotFoundResponseObject();
        assertThat(itemsNotFoundResponse.isError()).isEqualTo(expectedBooleanValue);
    }

    @Then("response body contains \"message\" field with {string} value")
    public void responseBodyContainsMessageFieldWithValue(String expectedValue) {
        ItemsNotFoundResponse itemsNotFoundResponse = foodService.getCurrentResponseAsItemsNotFoundResponseObject();
        assertThat(itemsNotFoundResponse.getMessage()).isEqualTo(expectedValue);
    }

    @Then("response body contains \"requested_item\" field with {string} value")
    public void responseBodyContainsRequestedItemFieldWithValue(String expectedValue) {
        ItemsNotFoundResponse itemsNotFoundResponse = foodService.getCurrentResponseAsItemsNotFoundResponseObject();
        assertThat(itemsNotFoundResponse.getRequested_item()).isEqualTo(expectedValue);
    }

    @Then("response body contains {string} error message")
    public void responseBodyContainsErrorMessage(String errorMessage) {
        ErrorResponse itemsNotFoundResponse = foodService.getCurrentResponseAsErrorResponseObject();
        assertThat(itemsNotFoundResponse.getDetail()).isEqualTo(errorMessage);
    }
}
