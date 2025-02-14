public class Customer {
    private int customerId;
    private String customerName;
    private String customerPhone;
    private String customerEmail;

    public Customer(int customerId, String customerName, String customerPhone, String customerEmail) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.customerEmail = customerEmail;
    }

    public int getCustomerId() { return customerId; }
    public String getCustomerName() { return customerName; }
    public String getCustomerPhone() { return customerPhone; }
    public String getCustomerEmail() { return customerEmail; }
}
