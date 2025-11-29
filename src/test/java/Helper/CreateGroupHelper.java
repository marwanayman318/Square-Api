package Helper;

import io.restassured.response.Response;
import utilities.Config;

import static io.restassured.RestAssured.given;

public class CreateGroupHelper {

    public static String createGroup(String groupName) {

        String body = "{ \"group\": { \"name\": \"" + groupName + "\" } }";

        Response res = given()
                .baseUri(Config.BASE_URL)
                .header("Authorization", Config.TOKEN)
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post("/customers/groups")
                .then()
                .statusCode(200)
                .extract().response();

        return res.jsonPath().getString("group.id");
    }

    /**
     * Creates multiple groups and returns list of IDs.
     */
    public static java.util.List<String> createGroupIds(int count) {

        java.util.List<String> groupIds = new java.util.ArrayList<>();

        for (int i = 1; i <= count; i++) {
            String groupName = "AutoGroup_" + System.currentTimeMillis() + "_" + i;
            String id = createGroup(groupName);
            groupIds.add(id);
        }

        return groupIds;
    }
}
