package servicelayer.employee;

import datalayer.employee.EmployeeStorage;
import dto.Employee;
import dto.EmployeeCreation;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

public class EmployeeServiceImpl implements EmployeeService{
    private EmployeeStorage employeeStorage;

    public EmployeeServiceImpl(EmployeeStorage empstorage) {
        this.employeeStorage = empstorage;
    }

    @Override
    public int createEmployee(String firstName, String lastName, Date birthdate) throws EmployeeServiceException {
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
        return null;
    }

    @Override
    public Collection<Employee> getAllEmployees() throws EmployeeServiceException {
        return null;
    }
}
