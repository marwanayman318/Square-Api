package payments.data;

import org.testng.annotations.DataProvider;

public class ListPaymentsData {
    @DataProvider(name = "listPaymentsData")
    public Object[][] listPaymentsData() {
        return new Object[][]{
                {"valid", 5, null, 200, "List payments with valid limit"},
                {"limit", 150, null, 200, "List payments exceeding limit"},
                {"limit", 0, null, 400, "limit must be positive"},
                {"begin_time", "2025-11-05T17:00:05.246Z", null, 200, "Payments should be newer than begin_time"},
                {"begin_time", "invalid-date", null, 400, "Invalid begin_time format"},
                {"end_time", "2025-11-04T17:00:05.246Z", null, 200, "List with end_time"},
                {"sort_order", "ASC", null, 200, "Sort ascending"},
                {"cursor", "eyJjcmVhdGVkQXQiOjE3NjIzNjAyMjIzMjcsImlkIjoiM0hPOE9qYjVWTE9YQWtpS25lWjNrdDdVcWRaWlkifQ", 1, 200, "Pagination with cursor"}
        };
    }
}
