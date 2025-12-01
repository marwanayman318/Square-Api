package checkout.Data;

import org.testng.annotations.DataProvider;

public class ListPaymentLinksData {
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
}
