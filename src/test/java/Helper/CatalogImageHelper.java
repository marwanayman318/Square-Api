package Helper;

import io.restassured.response.Response;
import utilities.Config;
import java.io.File;
import java.util.UUID;
import static io.restassured.RestAssured.given;

public class CatalogImageHelper {

    public static String createCatalogImage(String path) {

        try {
            File imageFile = new File("src/test/resources/" + path);

            if (!imageFile.exists()) {
                throw new RuntimeException("Image file not found: " + imageFile.getAbsolutePath());
            }
            String idempotencyKey = UUID.randomUUID().toString();
            String objectid = CreateItemHelper.getAnyCatalogObjectId();

            String meta = "{\n" +
                    "   \"idempotency_key\": \"" + idempotencyKey + "\",\n" +
                    "   \"image\":{\n" +
                    "   \"type\": \"IMAGE\",\n" +
                    "   \"id\": \"#Coffee_Image\", \n" +
                    "   \"image_data\":{\n" +
                    "   \"name\":\"Coffee Image\" \n" +
                    "           }\n" +
                    "       },\n" +
                    "   \"object_id\":\"" + objectid + "\" \n" +
                    "}";


            Response res =
                    given()
                            .baseUri(Config.BASE_URL)
                            .header("Authorization", Config.TOKEN)
                            .multiPart("file", imageFile, "image/png")
                            .multiPart("request", meta, "application/json")
                            .when()
                            .post("/catalog/images")
                            .then()
                            .extract()
                            .response();

            System.out.println("Catalog Image Response:");
            res.prettyPrint();

            if (res.getStatusCode() != 200) {
                throw new RuntimeException("Failed to create catalog image. "
                        + "Status " + res.getStatusCode() + res.asString());
            }

            return res.jsonPath().getString("image.id");

        } catch (Exception e) {
            throw new RuntimeException("Error creating catalog image", e);
        }
    }
}

