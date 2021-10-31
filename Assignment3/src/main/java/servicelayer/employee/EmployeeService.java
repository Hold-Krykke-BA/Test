package servicelayer.employee;

import dto.Employee;
import java.util.Collection;

public interface EmployeeService {
    int createEmployee(String firstName, String lastName) throws EmployeeServiceException;
    Employee getEmployeeById(int id) throws EmployeeServiceException;
    Collection<Employee> getEmployeesByFirstname(String firstName) throws EmployeeServiceException;
    Collection<Employee> getAllEmployees() throws EmployeeServiceException;
}
