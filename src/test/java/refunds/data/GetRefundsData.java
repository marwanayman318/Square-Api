package refunds.data;

import org.testng.annotations.DataProvider;

public class GetRefundsData {

    @DataProvider(name = "getRefundData")
    public Object[][] getRefundData() {
        return new Object[][]{
                {
                        "Create_TC_010 - Retrieve valid refund",
                        "fx1w3eSfPfhHoAqR0dJevcq6K9TZY_87uPs8iKpVIYjTHZ8fhGtEBiREwNUnVktTA9T722L0O", // ✅ Valid refund ID
                        200,
                        true
                },
                {
                        "Create_TC_011 - Retrieve invalid refund",
                        "fx1w3eSfPfhHoAqR0dJevcq6K9TZY_87uPs8iKpVIYjTHZ8fhGtEBiREwNUnVktTA9T722L0o", // ❌ Invalid refund ID (note lowercase 'o' at end)
                        404,
                        false
                }
        };
    }
}
