package integration.datalayer.employee;

import com.github.javafaker.Faker;
import datalayer.employee.EmployeeStorage;
import datalayer.employee.EmployeeStorageImpl;
import dto.EmployeeCreation;
import integration.ContainerizedDbIntegrationTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("integration")
class EmployeeIntegrationTest extends ContainerizedDbIntegrationTest {
    private EmployeeStorage employeeStorage;

    @BeforeAll
    public void Setup() throws SQLException {
        runMigration(4);
        employeeStorage = new EmployeeStorageImpl(getConnectionString(), getDbUser(), getDbPassword());
    }

    @Test
    public void mustSaveEmployeeInDatabaseWhenCallingCreateEmployee() throws SQLException {
        // Arrange
        // Act
        employeeStorage.createEmployee(new EmployeeCreation("Orla","Olsen"));

        // Assert
        var customers = employeeStorage.getEmployees();
        assertTrue(
                customers.stream().anyMatch(x ->
                        x.getFirstname().equals("Orla") &&
                                x.getLastname().equals("Olsen")));
    }

    @Test
    public void mustReturnLatestId() throws SQLException {
        // Arrange
        // Act
        var id1 = employeeStorage.createEmployee(new EmployeeCreation("Anders", "Ankersen"));
        var id2 = employeeStorage.createEmployee(new EmployeeCreation("Børge", "Bentsen"));
        // Assert
        assertEquals(1, id2 - id1);
    }
}