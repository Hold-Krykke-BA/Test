package servicelayer.employee;

import datalayer.employee.EmployeeStorage;
import dto.Employee;
import dto.EmployeeCreation;
import org.apache.commons.lang3.NotImplementedException;
import java.sql.SQLException;
import java.util.Collection;

public class EmployeeServiceImpl implements EmployeeService{
    private EmployeeStorage employeeStorage;

    public EmployeeServiceImpl(EmployeeStorage employeestorage) {
        this.employeeStorage = employeestorage;
    }

    @Override
    public int createEmployee(String firstName, String lastName) throws EmployeeServiceException {
        try {
            return employeeStorage.createEmployee(new EmployeeCreation(firstName, lastName));
        } catch (SQLException e) {
            throw new EmployeeServiceException(e.getMessage());
        }
    }

    @Override
    public Employee getEmployeeById(int id) throws EmployeeServiceException {
        try {
            return employeeStorage.getEmployeeWithId(id);
        } catch (SQLException e) {
            throw new EmployeeServiceException(e.getMessage());
        }
    }

    @Override
    public Collection<Employee> getEmployeesByFirstname(String firstName) throws EmployeeServiceException {
        throw new NotImplementedException("Method not implemented");
    }

    @Override
    public Collection<Employee> getAllEmployees() throws EmployeeServiceException {
        try {
            return employeeStorage.getEmployees();
        } catch (SQLException e) {
            throw new EmployeeServiceException(e.getMessage());
        }
    }
}
