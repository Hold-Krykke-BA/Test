package integration.servicelayer.employee;

import com.github.javafaker.Faker;
import datalayer.employee.EmployeeStorage;
import datalayer.employee.EmployeeStorageImpl;
import dto.Employee;
import integration.ContainerizedDbIntegrationTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import servicelayer.employee.EmployeeService;
import servicelayer.employee.EmployeeServiceException;
import servicelayer.employee.EmployeeServiceImpl;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EmployeeSvcTest extends ContainerizedDbIntegrationTest {
    private Faker faker;
    private EmployeeService svc;
    private EmployeeStorage storage;

    @BeforeAll
    public void setup() {
        runMigration(4);
        storage = new EmployeeStorageImpl(getConnectionString(), getDbUser(), getDbPassword());
        svc = new EmployeeServiceImpl(storage);
        faker = new Faker();
    }

    @Test
    public void mustSaveEmployeeToDatabaseWhenCallingCreateEmployee() throws EmployeeServiceException, SQLException {
        // Arrange
        String firstName = "Orla";
        String lastName = "Olsen";
        int id = svc.createEmployee("Orla","Olsen");

        // Act
        Employee createdEmployee = storage.getEmployeeWithId(id);

        // Assert
        assertEquals(firstName, createdEmployee.getFirstname());
        assertEquals(lastName, createdEmployee.getLastname());
    }
}