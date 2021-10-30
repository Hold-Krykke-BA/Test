package servicelayer.employee;

import dto.Employee;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

public interface EmployeeService {
    int createEmployee(String firstName, String lastName, Date birthdate) throws EmployeeServiceException;
    Employee getEmployeeById(int id) throws EmployeeServiceException;
    Collection<Employee> getEmployeesByFirstname(String firstName) throws EmployeeServiceException;
    Collection<Employee> getAllEmployees() throws EmployeeServiceException;
}
