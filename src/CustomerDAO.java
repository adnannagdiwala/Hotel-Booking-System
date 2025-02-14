import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    public void addCustomer(Customer customer) throws SQLException {
        String query = "INSERT INTO Customers (customer_name, customer_phone, customer_email) VALUES (?, ?, ?)";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, customer.getCustomerName());
            ps.setString(2, customer.getCustomerPhone());
            ps.setString(3, customer.getCustomerEmail());
            ps.executeUpdate();
        }
    }

    public List<Customer> getAllCustomers() throws SQLException {
        String query = "SELECT * FROM Customers";
        List<Customer> customers = new ArrayList<>();
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer(rs.getInt("customer_id"), rs.getString("customer_name"),
                        rs.getString("customer_phone"), rs.getString("customer_email"));
                customers.add(customer);
            }
        }
        return customers;
    }
}
