package hw_3;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class MealPlanTest extends AbstractTest {

    private String mealPlanId;

    @Test
    void testCreateAndDeleteMeal() {
        String mealName = "oatmeal";

        int id = given()
                .pathParams("user_name", getUserName())
                .queryParam("hash", getHash())
                .body("{\n"
                        + " \"item\": \"oatmeal\",\n"
                        + " \"aisle\": \"Baking\",\n"
                        + " \"parse\": true\n"
                        + "}"
                )
                .log().all()
                .when()
                .post(getBaseUrl() + "mealplanner/{user_name}/shopping-list/items")
                .prettyPeek()
                .jsonPath()
                .getInt("id");

        String mealPlanResponse = Integer.toString(id);

        mealPlanId = given()
                .pathParams("user_name", getUserName())
                .queryParam("hash", getHash())
                .contentType(ContentType.JSON)
                .body(mealPlanResponse)
                .log().all()
                .when()
                .post("/generate")
                .then()
                .statusCode(200)
                .extract()
                .path("id");

        given()
                .pathParams("user_name", getUserName())
                .queryParam("hash", getHash())
                .log().all()
                .when()
                .delete("/" + mealPlanId)
                .then()
                .statusCode(401);
    }
}