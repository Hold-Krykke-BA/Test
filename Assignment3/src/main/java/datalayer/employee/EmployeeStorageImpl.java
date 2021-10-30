package datalayer.employee;

import dto.Employee;
import dto.EmployeeCreation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeStorageImpl implements EmployeeStorage{
    private final String connectionString;
    private final String username;
    private final String password;

    public EmployeeStorageImpl(String conStr, String user, String pass){
        connectionString = conStr;
        username = user;
        password = pass;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connectionString, username, password);
    }

    @Override
    public Employee getEmployeeWithId(int employeeId) throws SQLException {
        String sql = "select ID, firstname, lastname from Employees where id = ?";
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, employeeId);

            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()){
                    int id = resultSet.getInt("ID");
                    String firstname = resultSet.getString("firstname");
                    String lastname = resultSet.getString("lastname");
                    return new Employee(id, firstname, lastname);
                }
                return null;
            }
        }
    }

    @Override
    public int createEmployee(EmployeeCreation employeeCreation) throws SQLException {
        String sql = "insert into Employees(firstname, lastname) values (?, ?)";
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, employeeCreation.getFirstname());
            stmt.setString(2, employeeCreation.getLastname());
            stmt.executeUpdate();

            // get the newly created id
            try (ResultSet resultSet = stmt.getGeneratedKeys()) {
                resultSet.next();
                int createdId = resultSet.getInt(1);
                return createdId;
            }
        }
    }

    @Override
    public List<Employee> getEmployees() throws SQLException {
        try (Connection con = getConnection();
             Statement stmt = con.createStatement()) {
            ArrayList results = new ArrayList<Employee>();

            try (ResultSet resultSet = stmt.executeQuery("select ID, firstname, lastname from Employees")) {

                while (resultSet.next()) {
                    int id = resultSet.getInt("ID");
                    String firstname = resultSet.getString("firstname");
                    String lastname = resultSet.getString("lastname");

                    Employee emp = new Employee(id, firstname, lastname);
                    results.add(emp);
                }
            }
            return results;
        }
    }
}
