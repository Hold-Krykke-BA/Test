package servicelayer.customer;

import dto.Customer;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

public interface CustomerService {
    int createCustomer(String firstName, String lastName, String phone, Date birthdate) throws CustomerServiceException;
    Customer getCustomerById(int id) throws SQLException;
    Collection<Customer> getAllCustomers() throws CustomerServiceException;
    Collection<Customer> getCustomersByFirstName(String firstName);
}
