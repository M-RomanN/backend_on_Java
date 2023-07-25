package hw_3;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PostRequests extends AbstractTest {
    @Test
    void fried_eggs_title() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("language", "en")
                .log().all()
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Fried eggs")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .statusCode(200)
                .time(lessThan(1000L))
                .contentType(ContentType.JSON);
    }

    @Test
    void fried_eggs_ingredients() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("language", "en")
                .log().all()
                .contentType("application/x-www-form-urlencoded")
                .formParam("ingredientlist", "2 eggs,\n1 pinch salt,\nsome oil of frying")
                .when()
                .post(getBaseUrl() + "recipes/cuisine")
                .then()
                .statusCode(200)
                .time(lessThan(1000L))
                .contentType(ContentType.JSON);
    }

    @Test
    void cuisineFinding_lang_de() {
        Response response = given()
                .queryParam("apiKey", getApiKey())
                .queryParam("language", "de")
                .log().all()
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Titel auf Deutsch")
                .when()
                .post(getBaseUrl() + "recipes/cuisine");

        assertThat(response.getStatusCode(), anyOf(is(200)));
    }

    @Test
    void cuisineFinding_lang_ru() {
        Response response = given()
                .queryParam("apiKey", getApiKey())
                .queryParam("language", "ru")
                .log().all()
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "Название на русском")
                .when()
                .post(getBaseUrl() + "recipes/cuisine");

        assertThat(response.getStatusCode(), anyOf(is(500)));
    }
    @Test
    void strangeTitle() {
        Response response = given()
                .queryParam("apiKey", getApiKey())
                .queryParam("language", "en")
                .log().all()
                .contentType("application/x-www-form-urlencoded")
                .formParam("title", "@@$#1qrfdgfs999999")
                .when()
                .post(getBaseUrl() + "recipes/cuisine");

        assertThat(response.getStatusCode(), anyOf(is(200)));


    }

}

