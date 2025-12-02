package Helper;

import POJOS.Customer.CustomerRequest;
import io.restassured.response.Response;
import services.POSTService;

import java.util.ArrayList;
import java.util.List;

public class CreateCustomerHelper {

    public static String createCustomerId() {
        // Build request body
        CustomerRequest request = new CustomerRequest();
        request.setGiven_name("AutoUser");
        request.setFamily_name("Test");
        request.setEmail_address("auto.user." + System.currentTimeMillis() + "@example.com");
        String endpoint = "/customers";
        Response response = POSTService.create(endpoint,request,200);
        return response.jsonPath().getString("customer.id");
    }

    public static List<String> createCustomerIds(int count) {
        List<String> ids = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            ids.add(createCustomerId());
        }

        return ids;
    }
}
