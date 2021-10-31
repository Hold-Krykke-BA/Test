package integration.datalayer.customer;

import com.github.javafaker.Faker;
import datalayer.customer.CustomerStorage;
import datalayer.customer.CustomerStorageImpl;
import dto.CustomerCreation;
import integration.ContainerizedDbIntegrationTest;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.SQLException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("integration")

class CreateCustomerTest { //} extends ContainerizedDbIntegrationTest {
    private CustomerStorage customerStorage;

    /* changed code */

    @BeforeAll
    public void Setup() throws SQLException {
        var url = "jdbc:mysql://localhost:3307/";
        var db = "BookingSystemTest";

        Flyway flyway = new Flyway(new FluentConfiguration()
                .defaultSchema(db)
                .createSchemas(true)
                .schemas(db)
                .target("4")
                .dataSource(url, "root", "holdkrykke"));
        flyway.migrate();
        customerStorage = new CustomerStorageImpl(url+db, "root", "holdkrykke");

        var numCustomers = customerStorage.getCustomers().size();
        if (numCustomers < 5) {
            addFakeCustomers(5 - numCustomers);
        }
//        runMigration(2);
//
//        customerStorage = new CustomerStorageImpl(getConnectionString(), "root", getDbPassword());
//
//        var numCustomers = customerStorage.getCustomers().size();
//        if (numCustomers < 100) {
//            addFakeCustomers(100 - numCustomers);
//        }
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
        var customers = customerStorage.getCustomers();
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
