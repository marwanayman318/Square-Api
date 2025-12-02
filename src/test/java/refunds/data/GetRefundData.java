package refunds.data;

import org.testng.annotations.DataProvider;

public class GetRefundData {
    @DataProvider(name = "GetRefundData")
    public Object[][] getRefundData() {
        return new Object[][]{
                {"TC_Refund_010", "fx1w3eSfPfhHoAqR0dJevcq6K9TZY_87uPs8iKpVIYjTHZ8fhGtEBiREwNUnVktTA9T722L0O",200,true},
                {"TC_Refund_011", "fx1w3eSfPfhHoAqR0dJevcq6K9TZY_87uPs8iKpVIYjTHZ8fhGtEBiREwNUnVktTA9T722L0o", 404, false}
        };
    }
}
