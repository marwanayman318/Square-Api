package checkout.Data;

import org.testng.annotations.DataProvider;

public class CreatePaymentLinkData {

    @DataProvider(name = "createPaymentLinkData")
    public Object[][] createPaymentLinkData() {

        return new Object[][]{
                {
                        "CPL_TC_012 - Create valid payment link",
                        "/online-checkout/payment-links",
                        "{\n" +
                                "  \"idempotency_key\": \"123e4567-e89b-12d3-a456-426614174000\",\n" +
                                "  \"quick_pay\": {\n" +
                                "    \"name\": \"Auto Detailing\",\n" +
                                "    \"price_money\": {\n" +
                                "      \"amount\": 1000,\n" +
                                "      \"currency\": \"USD\"\n" +
                                "    },\n" +
                                "    \"location_id\": \"L7NA82DE840VB\"\n" +
                                "  }\n" +
                                "}",
                        200,
                        true
                },

                {
                        "CPL_TC_013 - Missing location_id (Invalid Body)",
                        "/online-checkout/payment-links",
                        "{\n" +
                                "  \"idempotency_key\": \"123e4567-e89b-12d3-a456-426614174001\",\n" +
                                "  \"quick_pay\": {\n" +
                                "    \"name\": \"Auto Detailing\",\n" +
                                "    \"price_money\": {\n" +
                                "      \"amount\": 1000,\n" +
                                "      \"currency\": \"USD\"\n" +
                                "    }\n" +
                                "  }\n" +
                                "}",
                        400,
                        false
                }
        };
    }
}
