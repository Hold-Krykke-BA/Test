package datalayer.customer;

import dto.Employee;
import dto.EmployeeCreation;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class EmployeeStorageImpl implements EmployeeStorage{
    @Override
    public Collection<Employee> getEmployeeWithId(int employeeId) throws SQLException {
        return null;
    }

    @Override
    public int createEmployee(EmployeeCreation employeeCreation) throws SQLException {
        return 0;
    }

    @Override
    public List<Employee> getEmployees() throws SQLException {
        return null;
    }
}
