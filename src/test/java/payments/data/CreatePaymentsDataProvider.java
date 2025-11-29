package payments.data;

import org.testng.annotations.DataProvider;

public class CreatePaymentsDataProvider {
    @DataProvider(name = "PaymentsData")
    public Object[][] createData() {
        return new Object[][]{
                {2500, "USD", "cnon:card-nonce-ok", true, 200},
                {0, "USD", "cnon:card-nonce-ok", true, 400},
                {1500, "EGG", "cnon:card-nonce-ok", true, 400},
                {1200, "USD", "cnon:card-nonce-ok", false, 200},
                {-2500, "USD", "cnon:card-nonce-ok", true, 400}
        };
    }
}
