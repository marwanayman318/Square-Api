package services;

import POJOS.listPayment.listPaymentRequests;
import io.restassured.response.Response;
import utilities.Config;

import static io.restassured.RestAssured.given;

public class GETService {

    //list payment method to be used in tests
    public static Response listWithParam(listPaymentRequests request, int expectedStatus,String endPoint) {
        return given()
                .baseUri(Config.BASE_URL)
                .header("Authorization", Config.TOKEN)
                .queryParam("limit", request.getLimit())
                .queryParam("begin_time", request.getBeginTime())
                .queryParam("end_time", request.getEndTime())
                .queryParam("sort_order", request.getSortOrder())
                .queryParam("cursor", request.getCursor())
                .when()
                .get(endPoint)
                .then()
                .statusCode(expectedStatus)
                .extract().response();
    }

    public static Response list(String endpoint, int expectedStatus) {
        return given()
                .baseUri(Config.BASE_URL)
                .header("Authorization", Config.TOKEN)
                .header("Content-Type", "application/json")
                .when()
                .get(endpoint)
                .then()
                .statusCode(expectedStatus)
                .extract().response();
    }

    public static Response listWithToken(String endpoint, int expectedStatus, String token) {
        return given()
                .baseUri(Config.BASE_URL)
                .header("Authorization",token)
                .header("Content-Type", "application/json")
                .when()
                .get(endpoint)
                .then()
                .statusCode(expectedStatus)
                .extract()
                .response();
    }


}