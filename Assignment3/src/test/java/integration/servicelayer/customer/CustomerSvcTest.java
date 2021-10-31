package integration.servicelayer.customer;

import com.github.javafaker.Faker;
import datalayer.customer.CustomerStorage;
import datalayer.customer.CustomerStorageImpl;
import dto.Customer;
import integration.ContainerizedDbIntegrationTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import servicelayer.customer.CustomerService;
import servicelayer.customer.CustomerServiceException;
import servicelayer.customer.CustomerServiceImpl;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CustomerSvcTest extends ContainerizedDbIntegrationTest {
    private Faker faker;
    private CustomerService svc;
    private CustomerStorage storage;

    @BeforeAll
    public void setup() {
        runMigration(4);
        storage = new CustomerStorageImpl(getConnectionString(), getDbUser(), getDbPassword());
        svc = new CustomerServiceImpl(storage);
        faker = new Faker();
    }

    @Test
    public void mustSaveCustomerToDatabaseWhenCallingCreateCustomer() throws CustomerServiceException, SQLException {
        // Arrange
        String firstName = "Orla";
        String lastName = "Olsen";
        int id = svc.createCustomer("Orla","Olsen", faker.phoneNumber().subscriberNumber(8), faker.date().birthday());

        // Act
        Customer createdCustomer = storage.getCustomerWithId(id);

        // Assert
        assertEquals(firstName, createdCustomer.getFirstname());
        assertEquals(lastName, createdCustomer.getLastname());
    }
}
