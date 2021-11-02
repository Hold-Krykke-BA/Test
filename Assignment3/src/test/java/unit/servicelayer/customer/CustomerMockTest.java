package unit.servicelayer.customer;

import com.github.javafaker.Faker;
import datalayer.customer.CustomerStorage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import servicelayer.customer.CustomerService;
import servicelayer.customer.CustomerServiceException;
import servicelayer.customer.CustomerServiceImpl;
import java.sql.SQLException;
import java.util.Date;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("unit")
public class CustomerMockTest {
    private CustomerService customerService;
    private CustomerStorage storageMock;
    private Faker faker;

    @BeforeAll
    public void beforeAll(){
        storageMock = mock(CustomerStorage.class);
        customerService = new CustomerServiceImpl(storageMock);
        faker = new Faker();
    }

    @Test
    public void TestCallStorageWhenCreatingCustomer() throws CustomerServiceException, SQLException {
        // Arrange
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String phone = faker.phoneNumber().subscriberNumber(8);
        Date birthdate = faker.date().birthday();
        // Act
        customerService.createCustomer(firstName, lastName, phone, birthdate);
        // Assert
        verify(storageMock, times(1))
                .createCustomer(
                        argThat(x -> x.getFirstname().equals(firstName) &&
                                x.getLastname().equals(lastName) && x.getPhoneNumber().equals(phone) && x.getBirthday().equals(birthdate)));
    }
}
