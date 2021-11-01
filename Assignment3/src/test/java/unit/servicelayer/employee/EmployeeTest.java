package unit.servicelayer.employee;

import com.github.javafaker.Faker;
import datalayer.employee.EmployeeStorage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import servicelayer.employee.EmployeeService;
import servicelayer.employee.EmployeeServiceException;
import servicelayer.employee.EmployeeServiceImpl;
import java.sql.SQLException;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("unit")
public class EmployeeTest {
    private Faker faker;
    private EmployeeService employeeService;
    private EmployeeStorage storageMock;

    @BeforeAll
    public void beforeAll(){
        storageMock = mock(EmployeeStorage.class);
        employeeService = new EmployeeServiceImpl(storageMock);
        faker = new Faker();
    }

    @Test
    public void TestCallStorageWhenCreatingEmployee() throws SQLException, EmployeeServiceException {
        // Arrange
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        // Act
        employeeService.createEmployee(firstName, lastName);
        // Assert
        verify(storageMock, times(1))
                .createEmployee(
                        argThat(x -> x.getFirstname().equals(firstName) &&
                                x.getLastname().equals(lastName)));
    }
}
