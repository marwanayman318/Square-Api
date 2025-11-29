package services;

import io.restassured.response.Response;
import utilities.Config;

import java.io.File;

import static io.restassured.RestAssured.given;

public class POSTService {

    public static Response create(String endpoint, Object request, int expectedStatus) {
        return given()
                .baseUri(Config.BASE_URL)
                .header("Authorization", Config.TOKEN)
                .header("Content-Type", "application/json")
                .body(request)
                .when()
                .post(endpoint)
                .then()
                .statusCode(expectedStatus)
                .extract().response();
    }

    public static Response createWithImage(String endpoint, String requestBody, String filePath, int expectedStatus) {

        File imageFile = new File(filePath);

        if (!imageFile.exists()) {
            throw new RuntimeException("IMAGE NOT FOUND: " + imageFile.getAbsolutePath());
        }

        return given()
                .baseUri(Config.BASE_URL)
                .header("Authorization", Config.TOKEN)
                .header("Accept", "application/json")
                .multiPart("file", imageFile, "image/png")
                .multiPart("request", requestBody, "application/json")
                .when()
                .post(endpoint)
                .then()
                .statusCode(expectedStatus)
                .extract().response();
    }
}
