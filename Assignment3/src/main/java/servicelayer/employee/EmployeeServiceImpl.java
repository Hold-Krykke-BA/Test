package servicelayer.employee;

import dto.Employee;

import java.util.Collection;
import java.util.Date;

public class EmployeeServiceImpl implements EmployeeService{
    @Override
    public int createEmployee(String firstName, String lastName, Date birthdate) throws EmployeeServiceException {
        return 0;
    }

    @Override
    public Employee getEmployeeById(int id) {
        return null;
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
