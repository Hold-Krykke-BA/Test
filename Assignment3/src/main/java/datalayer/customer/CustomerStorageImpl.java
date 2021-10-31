package datalayer.customer;

import dto.Customer;
import dto.CustomerCreation;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerStorageImpl implements CustomerStorage {
    private final String connectionString;
    private final String username;
    private final String password;

    public CustomerStorageImpl(String conStr, String user, String pass){
        connectionString = conStr;
        username = user;
        password = pass;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connectionString, username, password);
    }

    @Override
    public Customer getCustomerWithId(int customerId) throws SQLException {
        String sql = "select ID, firstname, lastname, phonenumber, birthdate from Customers where id = ?";
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, customerId);

            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()){
                    int id = resultSet.getInt("ID");
                    String firstname = resultSet.getString("firstname");
                    String lastname = resultSet.getString("lastname");
                    String phonenumber = resultSet.getString("phonenumber");
                    Date birthdate = resultSet.getDate("birthdate");
                    return new Customer(id, firstname, lastname, phonenumber, birthdate);
                }
                return null;
            }
        }
    }

    public List<Customer> getCustomers() throws SQLException {
        try (Connection con = getConnection();
             Statement stmt = con.createStatement()) {
            ArrayList<Customer> results = new ArrayList<>();

            ResultSet resultSet = stmt.executeQuery("select ID, firstname, lastname, birthdate, phonenumber from Customers");

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                String phonenumber = resultSet.getString("phonenumber");
                Date birthdate = resultSet.getDate("birthdate");
                Customer c = new Customer(id, firstname, lastname, phonenumber, birthdate);
                results.add(c);
            }
            return results;
        }
    }

    public int createCustomer(CustomerCreation customerToCreate) throws SQLException {
        String sql = "insert into Customers(firstname, lastname, birthdate, phonenumber) values (?, ?, ? , ?)";
        try (Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, customerToCreate.getFirstname());
            stmt.setString(2, customerToCreate.getLastname());
            stmt.setDate(3, new java.sql.Date(customerToCreate.getBirthday().getTime()));
            stmt.setString(4, customerToCreate.getPhoneNumber());
            stmt.executeUpdate();

            // get the newly created id
            try (ResultSet resultSet = stmt.getGeneratedKeys()) {
                resultSet.next();
                int createdId = resultSet.getInt(1);
                return createdId;
            }
        }
    }
}
