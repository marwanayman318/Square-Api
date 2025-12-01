package catalog.Data;

import org.testng.annotations.DataProvider;

public class SearchCatalogObjectsData {
    @DataProvider(name = "searchCatalogObjectsData")
    public static Object[][] searchCatalogObjectsData() {
        String body1 = "{\n " +
                "   \"object_types\": [\n " +
                "       \"ITEM\" \n " +
                "      ],\n" +
                " \"query\": {\n" +
                " \"prefix_query\": {\n" +
                " \"attribute_name\": \"name\", \n " +
                "\"attribute_prefix\": \"tea\" \n" +
                "       }\n" +
                "   },\n" +
                " \"limit\": 5 \n" +
                "}";
        String body2 = "{\n" +
                "   \"object_types\": [\n" +
                "   \"ITEM\" \n" +
                "   ],\n" +
                "   \"query\": {\n" +
                "   \"exact_query\": {\n" +
                "   \"attribute_name\": \"wrong_field\", \n" +
                "   \"attribute_value\": \"Value\" \n" +
                "}  \n" +
                " } \n" +
                " }";
        return new Object[][]{
                {"SCO_TC_015", body1, 200, true},
                {"SCO_TC_016", body2, 400, false}
        };

    }
}

