package checkout.Data;

import Helper.PaymentLinkHelper;
import org.testng.annotations.DataProvider;
import utilities.Config;

import static Helper.PaymentLinkHelper.createPaymentLink;

public class CheckoutData {

    //Create Payment Link Data Provider
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

    //Delete Payment Link Data Provider
    @DataProvider(name = "deletePaymentLinkData")
    public Object[][] deletePaymentLinkData() {
        String paymenLinkId = createPaymentLink();
        return new Object[][]{

                {
                        "DPL_TC_014",
                        paymenLinkId,
                        200,
                        true
                },

                {
                        "DPL_TC_015",
                        "JCE54XJJMBOV3BYP",
                        404,
                        false
                }
        };
    }
    //List Payment Links Data Provider
    @DataProvider(name = "listPaymentLinksData")
    public Object[][] getPaymentLinkData() {
        return new Object[][] {

                {
                        "LPL_TC_010 - List payment links successfully",
                        "/online-checkout/payment-links",
                        200,
                        true
                },

                {
                        "LPL_TC_011 - Invalid limit parameter but still succeed",
                        "/online-checkout/payment-links?limit=0",
                        200,
                        true
                },
                {
                        "LPL_TC_011b - Negative limit parameter but still succeed",
                        "/online-checkout/payment-links?limit=-1",
                        200,
                        true
                }
        };
    }

    //Retrieve Location Settings Data Provider
    @DataProvider(name = "locationSettingsData")
    public Object[][] locationSettingsData() {
        return new Object[][]{
                {"List_TC_001 - Valid location", "L7NA82DE840VB", 200, true},
                {"List_TC_002 - Invalid location (not found)", "L7NA82DE840Vb", 404, false}
        };
    }

    //retrieve merchant data provider
    @DataProvider(name = "retrieveMerchantSettingsData")
    public Object[][] getMerchantSettingsData() {
        return new Object[][]{
                {
                        "RMS_TC_006 - Valid merchant settings retrieval",
                        200,
                        true,
                        Config.TOKEN
                },
                {
                        "RMS_TC_007 - Invalid token returns 401",
                        401,
                        false,
                        "INVALID_TOKEN"
                }
        };
    }

    //Retrieve payment link
    @DataProvider(name = "retrievePaymentLinkData")
    public Object[][] retrievePaymentLinkData() {
        return new Object[][]{

                {
                        "RPL_TC_016 - Retrieve valid payment link",
                        "LYZAHQU77DP6F2OV",
                        200,
                        true
                },

                {
                        "RPL_TC_017 - Retrieve non-existing payment link",
                        "EZROVE6YV3ZQ7QBV5",
                        404,
                        false
                }

        };
    }

    // update location settings data provider
    @DataProvider(name = "updateLocationSettingsData")
    public Object[][] updateLocationSettingsData() {
        return new Object[][]{
                {
                        "Update branding settings (valid)",
                        "L7NA82DE840VB",
                        "{ \"location_settings\": { \"branding\": { \"button_color\": \"#00b23b\", \"button_shape\": \"ROUNDED\" } } }",
                        200,
                        true
                },
                {
                        "Update tipping settings (valid)",
                        "L7NA82DE840VB",
                        "{ \"location_settings\": { \"tipping\": { \"smart_tipping_enabled\": false, \"percentages\": [10, 15, 20] } } }",
                        200,
                        true
                },
                {
                        "Update_TC_005",
                        "L7NA82DE840VB",
                        "{ \"location_settings\": { \"branding\": { \"button_colour\": \"blue\" } } }",
                        400,
                        false
                }
        };
    }

    //update merchant settings data provider
    @DataProvider(name = "updateMerchantSettingsData")
    public Object[][] getData() {
        return new Object[][] {

                // VALID UPDATE
                {
                        "UMS_TC_008 - Update merchant settings valid",
                        "{ \"merchant_settings\": { \"branding\": { \"button_color\": \"#1122FF\" } } }",
                        200,
                        true
                },
                // INVALID BODY
                {
                        "UMS_TC_010 - Invalid body structure",
                        "{ }",
                        400,
                        false
                }
        };
    }

    //update payment link data provider
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
