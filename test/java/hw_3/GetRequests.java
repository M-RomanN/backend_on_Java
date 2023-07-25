package hw_3;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.lessThan;

public class GetRequests extends AbstractTest {

    @Test
    void instructionsRequired() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("instructionsRequired", "true")
                .log().all()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .statusCode(200)
                .time(lessThan(1000L))
                .statusLine(containsString("OK"))
                .contentType(ContentType.JSON);
    }

    @Test
    void diet() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("diet", "Ketogenic")
                .log().all()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .statusCode(200)
                .time(lessThan(100L))
                .statusLine(containsString("OK"))
                .contentType(ContentType.JSON);

    }

    @Test
    void minSugar() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("minSugar", "0")
                .log().all()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .statusCode(200)
                .time(lessThan(1000L))
                .statusLine(containsString("OK"))
                .contentType(ContentType.JSON);
    }

    @Test
    void maxCalories_maxAlcohol() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("maxCalories", "1000")
                .queryParam("maxAlcohol", "90")
                .log().all()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .statusCode(200)
                .time(lessThan(1000L))
                .statusLine(containsString("OK"))
                .contentType(ContentType.JSON);
    }

    @Test
    void Cuisine() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("cuisine", "Japanese")
                .queryParam("type", "soup", "drink", "dessert")
                .log().all()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .statusCode(200)
                .time(lessThan(1000L))
                .statusLine(containsString("OK"))
                .contentType(ContentType.JSON);
    }
}
