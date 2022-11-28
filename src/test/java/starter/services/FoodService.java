package starter.services;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import starter.models.ErrorResponse;
import starter.models.ItemsNotFoundResponse;
import starter.models.SearchResponse;

import java.util.Arrays;
import java.util.List;

public class FoodService {

    private Response currentResponse;
    private final String BASE_URL = "https://waarkoop-server.herokuapp.com/api/v1/";
    private final Gson gson;

    public FoodService() {
        gson = new Gson();
    }

    public void sendRequest(String requestPath, String httpMethod) {
        currentResponse = SerenityRest.rest().baseUri(BASE_URL).basePath(requestPath).request(httpMethod);
    }

    public int getStatusCodeOfCurrentResponse() {
        return currentResponse.getStatusCode();
    }

    public List<SearchResponse> getCurrentResponseAsSearchResponseObject() {
        return Arrays.asList(currentResponse.getBody().as(SearchResponse[].class, ObjectMapperType.GSON));
    }

    public ItemsNotFoundResponse getCurrentResponseAsItemsNotFoundResponseObject() {
        return gson.fromJson(currentResponse.getBody().as(JsonObject.class, ObjectMapperType.GSON)
                        .getAsJsonObject("detail"), ItemsNotFoundResponse.class);
    }

    public ErrorResponse getCurrentResponseAsErrorResponseObject() {
        return currentResponse.getBody().as(ErrorResponse.class, ObjectMapperType.GSON);
    }
}
