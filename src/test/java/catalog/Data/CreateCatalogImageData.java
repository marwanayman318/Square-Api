package catalog.Data;

import Helper.CreateItemHelper;
import org.testng.annotations.DataProvider;

import java.util.UUID;

public class CreateCatalogImageData {
    @DataProvider(name = "createCatalogImageData")
    public static Object[][] createCatalogImageData() {
        String idempotencyKey = UUID.randomUUID().toString();
        String imagePath1 = "src/test/resources/TC001_succ.png";
        String imagePath2 = "src/test/resources/Marwan-Ayman-CV.pdf";
        String objectId1 = CreateItemHelper.getAnyCatalogObjectId();
        String body1 = "{ \"idempotency_key\":\"" + idempotencyKey + "\", \n" +
                "\"image\":{\n" +
                " \"type\":\"IMAGE\",\n" +
                "\"id\":\"#Coffee_Image\",\n" +
                "\"image_data\":{\n" +
                " \"name\":\"Coffee Image\" \n" +
                "       }\n" +
                "   },\n" +
                " \"object_id\":\"" + objectId1 + "\" \n" +
                "}";
        return new Object[][]{
                {"CCI_TC_023", imagePath1,body1, 200, true},
                {"CCI_TC_024", imagePath2,body1, 400, false}
        };
    }


    @DataProvider(name = "updateCatalogImageData")
    public static Object[][] updateCatalogImageData() {
        String imagePath1 = "src/test/resources/TC001_succ.png";
        String imagePath2 = "src/test/resources/TC007_succ.png";
        String objectId = CreateItemHelper.getAnyCatalogObjectId();
        String request1 =
                "{\n" +
                        "  \"idempotency_key\": \"" + UUID.randomUUID() + "\",\n" +
                        "  \"object_id\": \"" + objectId + "\",\n" +
                        "  \"image\": {\n" +
                        "  \"type\": \"IMAGE\",\n" +
                        "  \"id\":\"#Coffee_Image\"," +
                        "    \"image_data\": {\n" +
                        "      \"name\": \"Coffee\",\n" +
                        "      \"caption\": \"A picture of a cup of coffee\"\n" +
                        "    }" +
                        "  }" +
                        "}";

        String request2 =
                "{\n" +
                        "  \"idempotency_key\": \"" + UUID.randomUUID() + "\",\n" +
                        "  \"object_id\": \"" + objectId + "\",\n" +
                        "  \"image\": {\n" +
                        "    \"type\": \"IMAGE\",\n" +
                        "    \"image_data\": {\n" +
                        "      \"name\": \"Coffee\",\n" +
                        "      \"caption\": \"A picture of a cup of coffee\"\n" +
                        "    }\n" +
                        "  }\n" +
                        "}";

        return new Object[][]{
                {"UCI_TC_013", "XZPLLCVPQLNSMJDHLVCCLY7Y", imagePath1, request1, 200, true},
                {"UCI_TC_014", "invalid_image_id", imagePath2, request2, 400, false}
        };

    }

}
