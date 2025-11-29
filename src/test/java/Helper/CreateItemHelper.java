package Helper;

import io.restassured.response.Response;
import utilities.Config;

import static io.restassured.RestAssured.given;

public class CreateItemHelper {

    public static String getAnyCatalogObjectId() {
        Response res = given()
                .baseUri(Config.BASE_URL)
                .header("Authorization", Config.TOKEN)
                .header("Content-Type", "application/json")
                .when()
                .get("/catalog/list")
                .then()
                .extract()
                .response();

        if (res.statusCode() != 200) {
            throw new RuntimeException(
                    "Failed to list catalog objects. Status: " +
                            res.statusCode() + " Body: " + res.asString());
        }

        int count = res.jsonPath().getList("objects").size();

        if (count == 0) {
            throw new RuntimeException("No catalog objects found in sandbox.");
        }

        return res.jsonPath().getString("objects[0].id");
    }
}
