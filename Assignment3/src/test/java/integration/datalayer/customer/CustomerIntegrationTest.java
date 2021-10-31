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
    public void Setup() {
        runMigration(4);
        customerStorage = new CustomerStorageImpl(getConnectionString(), getDbUser(), getDbPassword());
    }

    @Test
    public void mustSaveCustomerInDatabaseWhenCallingCreateCustomer() throws SQLException {
        // Arrange
        // Act
        customerStorage.createCustomer(new CustomerCreation("Orla","Olsen", "12345678", new Date()));
        // Assert
        List<Customer> customers = customerStorage.getCustomers();
        assertTrue(
                customers.stream().anyMatch(x ->
                        x.getFirstname().equals("Orla") &&
                        x.getLastname().equals("Olsen")));
    }

    @Test
    public void mustReturnLatestId() throws SQLException {
        // Arrange
        Faker faker = new Faker();
        // Act
        int id1 = customerStorage.createCustomer(new CustomerCreation("Anders", "Ankersen", faker.phoneNumber().subscriberNumber(8), faker.date().birthday()));
        int id2 = customerStorage.createCustomer(new CustomerCreation("Børge", "Bentsen", faker.phoneNumber().subscriberNumber(8), faker.date().birthday()));
        // Assert
        assertEquals(1, id2 - id1);
    }
}
