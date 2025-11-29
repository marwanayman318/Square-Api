package POJOS.listPayment;

public class listPaymentRequests {
    private Integer limit;
    private String beginTime;
    private String endTime;
    private String sortOrder;
    private String cursor;
    private String token;

    public Integer getLimit() { return limit; }
    public void setLimit(Integer limit) { this.limit = limit; }

    public String getBeginTime() { return beginTime; }
    public void setBeginTime(String beginTime) { this.beginTime = beginTime; }

    public String getEndTime() { return endTime; }
    public void setEndTime(String endTime) { this.endTime = endTime; }

    public String getSortOrder() { return sortOrder; }
    public void setSortOrder(String sortOrder) { this.sortOrder = sortOrder; }

    public String getCursor() { return cursor; }
    public void setCursor(String cursor) { this.cursor = cursor; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}

