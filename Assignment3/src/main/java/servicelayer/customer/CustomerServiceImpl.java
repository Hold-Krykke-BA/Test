package servicelayer.customer;

import datalayer.customer.CustomerStorage;
import dto.Customer;
import dto.CustomerCreation;
import org.apache.commons.lang3.NotImplementedException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

public class CustomerServiceImpl implements CustomerService {

    private CustomerStorage customerStorage;

    public CustomerServiceImpl(CustomerStorage customerStorage) {
        this.customerStorage = customerStorage;
    }

    @Override
    public int createCustomer(String firstName, String lastName, String phone, Date birthdate) throws CustomerServiceException {
        try {
            return customerStorage.createCustomer(new CustomerCreation(firstName, lastName, phone, birthdate));
        } catch (SQLException e) {
            throw new CustomerServiceException(e.getMessage());
        }
    }

    @Override
    public Customer getCustomerById(int id) throws SQLException {
        return customerStorage.getCustomerWithId(id);
    }

    @Override
    public Collection<Customer> getAllCustomers() throws CustomerServiceException {
        try {
            return customerStorage.getCustomers();
        } catch (SQLException e) {
            throw new CustomerServiceException(e.getMessage());
        }
    }

    @Override
    public Collection<Customer> getCustomersByFirstName(String firstName) {
        throw new NotImplementedException("Method not implemented");
    }
}
