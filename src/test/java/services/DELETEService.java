package services;

import io.restassured.response.Response;
import utilities.Config;

import static io.restassured.RestAssured.given;

public class DELETEService {
    public static Response delete(String endpoint, int expectedStatus) {

        return   given()
                .baseUri(Config.BASE_URL)
                .given()
                .header("Authorization", Config.TOKEN)
                .header("Content-Type", "application/json")
                .when()
                .delete(endpoint)
                .then()
                .statusCode(expectedStatus)
                .extract().response();
    }
}
