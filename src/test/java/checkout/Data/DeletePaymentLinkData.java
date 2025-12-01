package checkout.Data;

import org.testng.annotations.DataProvider;
import static Helper.PaymentLinkHelper.createPaymentLink;

public class DeletePaymentLinkData {

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
}
