package datalayer.employee;
import dto.Employee;
import dto.EmployeeCreation;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public interface EmployeeStorage {
    public Employee getEmployeeWithId(int employeeId) throws SQLException;
    public int  createEmployee(EmployeeCreation employeeCreation) throws SQLException;
    public List<Employee> getEmployees() throws SQLException;
}