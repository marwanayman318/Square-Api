package payments.tests;

import POJOS.listPayment.listPaymentRequests;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import payments.data.ListPaymentsData;
import services.GETService;
import utilities.Config;
import java.util.List;

@Epic("Payments API")
@Feature("List All Payment")
public class ListPaymentsTest extends ListPaymentsData {

    @Test(dataProvider = "listPaymentsData")
    public void listPayments(String paramType, Object value, Object value2, int expectedStatus, String description) {
        System.out.println("Running: " + description);

        String endPoint = "/payments";

        listPaymentRequests request = new listPaymentRequests();
        request.setToken(paramType.equals("noPayments") ? Config.TOKEN2 : Config.TOKEN);

        switch (paramType) {
            case "valid":
            case "limit":
                request.setLimit((Integer) value);
                break;
            case "begin_time":
                request.setBeginTime((String) value);
                break;
            case "end_time":
                request.setEndTime((String) value);
                break;
            case "sort_order":
                request.setSortOrder((String) value);
                break;
            case "cursor":
                request.setCursor((String) value);
                request.setLimit((Integer) value2);
                break;
        }

        Response res = GETService.listWithParam(request, expectedStatus,endPoint);

        res.prettyPrint();


        if (expectedStatus == 200) {
            String body = res.getBody().asString().trim();
            if (paramType.equals("noPayments")) {

                List<Object> payments = res.jsonPath().getList("payments");
                int count = (payments != null) ? payments.size() : 0;
                Assert.assertEquals(count, 0, "Expected no payments for token2 (sandbox)");
            } else {

                Assert.assertTrue(body.contains("\"payments\"") || body.contains("payments"),
                        "Response body does not contain 'payments' key: " + body);
            }
        }

        System.out.println(description + " Passed\n");
    }


}
