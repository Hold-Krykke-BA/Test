package integration.datalayer.customer;

import com.github.javafaker.Faker;
import datalayer.customer.CustomerStorage;
import datalayer.customer.CustomerStorageImpl;
import dto.Customer;
import dto.CustomerCreation;
import integration.ContainerizedDbIntegrationTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("integration")
class CustomerIntegrationTest extends ContainerizedDbIntegrationTest {
    private CustomerStorage customerStorage;

    @BeforeAll
    public void Setup() throws SQLException {
        runMigration(4);
        customerStorage = new CustomerStorageImpl(getConnectionString(), getDbUser(), getDbPassword());

        int numCustomers = customerStorage.getCustomers().size();
        if (numCustomers < 100) {
            addFakeCustomers(100 - numCustomers);
        }
    }

    private void addFakeCustomers(int numCustomers) throws SQLException {
        Faker faker = new Faker();
        for (int i = 0; i < numCustomers; i++) {
            CustomerCreation c = new CustomerCreation(faker.name().firstName(), faker.name().lastName(), faker.phoneNumber().subscriberNumber(8), faker.date().birthday());
            customerStorage.createCustomer(c);
        }
    }

    @Test
    public void mustSaveCustomerInDatabaseWhenCallingCreateCustomer() throws SQLException {
        // Arrange
        // Act
        customerStorage.createCustomer(new CustomerCreation("John","Carlssonn", "12345678", new Date()));
        // Assert
        List<Customer> customers = customerStorage.getCustomers();
        assertTrue(
                customers.stream().anyMatch(x ->
                        x.getFirstname().equals("John") &&
                        x.getLastname().equals("Carlssonn")));
    }

    @Test
    public void mustReturnLatestId() throws SQLException {
        // Arrange
        Faker faker = new Faker();
        // Act
        int id1 = customerStorage.createCustomer(new CustomerCreation("Anders", "Ankersen", faker.phoneNumber().phoneNumber(), faker.date().birthday()));
        int id2 = customerStorage.createCustomer(new CustomerCreation("Børge", "Bentsen", faker.phoneNumber().phoneNumber(), faker.date().birthday()));

        // Assert
        assertEquals(1, id2 - id1);
    }
}
