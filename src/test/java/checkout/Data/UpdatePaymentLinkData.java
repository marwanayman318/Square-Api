package checkout.Data;

import Helper.PaymentLinkHelper;
import org.testng.annotations.DataProvider;

public class UpdatePaymentLinkData {
    @DataProvider(name = "updatePaymentLinkData")
    public Object[][] updatePaymentLinkData() {

        // Create fresh link for every full test run
        String paymentId = PaymentLinkHelper.createPaymentLink();

        return new Object[][]{
                {
                        "UPL_TC_018 - Update valid field",
                        paymentId,
                        "{\n" +
                                "  \"payment_link\": {\n" +
                                "    \"version\": {{version}},\n" +
                                "    \"checkout_options\": {\n" +
                                "      \"ask_for_shipping_address\": true\n" +
                                "    }\n" +
                                "  }\n" +
                                "}",
                        200,
                        true
                },
                {
                        "UPL_TC_019 - Attempt to update non-updatable field",
                        paymentId,
                        "{\n" +
                                "  \"payment_link\": {\n" +
                                "    \"version\": {{version}},\n" +
                                "    \"order_id\": \"XKGOigNbb0aomrDgsPPL8fhqx7JZ8\",\n" +
                                "    \"checkout_options\": {\n" +
                                "      \"ask_for_shipping_address\": true\n" +
                                "    }\n" +
                                "  }\n" +
                                "}",
                        400,
                        false
                }
        };
    }
}
