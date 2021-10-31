package integration.datalayer.booking;

import com.github.javafaker.Faker;
import datalayer.booking.BookingStorage;
import datalayer.booking.BookingStorageImpl;
import datalayer.customer.CustomerStorage;
import datalayer.customer.CustomerStorageImpl;
import datalayer.employee.EmployeeStorage;
import datalayer.employee.EmployeeStorageImpl;
import dto.Booking;
import dto.BookingCreation;
import dto.CustomerCreation;
import dto.EmployeeCreation;
import integration.ContainerizedDbIntegrationTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("integration")
class BookingIntegrationTest extends ContainerizedDbIntegrationTest {
    private BookingStorage bookingStorage;
    private CustomerStorage customerStorage;
    private EmployeeStorage employeeStorage;
    Date date = new Date();

    @BeforeAll
    public void Setup() throws SQLException {
        runMigration(4);
        bookingStorage = new BookingStorageImpl(getConnectionString(), getDbUser(), getDbPassword());
        customerStorage = new CustomerStorageImpl(getConnectionString(), getDbUser(), getDbPassword());
        employeeStorage = new EmployeeStorageImpl(getConnectionString(), getDbUser(), getDbPassword());

        int numCustomers = customerStorage.getCustomers().size();
        if (numCustomers < 5) {
            addFakeCustomers(5 - numCustomers);
        }
        int numEmployees = employeeStorage.getEmployees().size();
        if (numCustomers < 5) {
            addFakeEmployees(5 - numEmployees);
        }
    }
    private void addFakeCustomers(int numCustomers) throws SQLException {
        Faker faker = new Faker();
        for (int i = 0; i < numCustomers; i++) {
            CustomerCreation c = new CustomerCreation(faker.name().firstName(), faker.name().lastName(), faker.phoneNumber().subscriberNumber(8), faker.date().birthday());
            customerStorage.createCustomer(c);
        }
    }

    private void addFakeEmployees(int numEmployees) throws SQLException {
        Faker faker = new Faker();
        for (int i = 0; i < numEmployees; i++) {
            EmployeeCreation e = new EmployeeCreation(faker.name().firstName(), faker.name().lastName());
            employeeStorage.createEmployee(e);
        }
    }

    @Test
    public void mustSaveBookingInDatabaseWhenCallingCreateBooking() throws SQLException {
        // Arrange
        int empID = employeeStorage.getEmployees().get(0).getId();
        int cusID = customerStorage.getCustomers().get(0).getId();
        // Act
        bookingStorage.createBooking(new BookingCreation(cusID, empID, new java.sql.Date(date.getTime()), "18:00", "21:30"));

        // Assert
        Collection<Booking> customers = bookingStorage.getBookingsForCustomer(cusID);
        assertTrue(
                customers.stream().anyMatch(x ->
                        x.getCustomerId() == cusID &&
                                x.getEmployeeId() == empID));
    }

    @Test
    public void mustReturnLatestId() throws SQLException {
        // Arrange
        // Act
        int id1 =  bookingStorage.createBooking(new BookingCreation(1, 1, new java.sql.Date(date.getTime()), "15:00", "21:00"));
        int id2 =  bookingStorage.createBooking(new BookingCreation(1, 1, new java.sql.Date(date.getTime()), "15:00", "21:00"));

        // Assert
        assertEquals(1, id2 - id1);
    }
}