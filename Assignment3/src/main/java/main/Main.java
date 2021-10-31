package main;

import com.github.javafaker.Faker;
import datalayer.employee.EmployeeStorageImpl;
import datalayer.customer.CustomerStorageImpl;
import dto.CustomerCreation;
import dto.EmployeeCreation;

import java.sql.SQLException;

public class Main {

    private static final String conStr = "jdbc:mysql://localhost:3307/BookingSystem";
    private static final String user = "root";
    private static final String pass = "holdkrykke";

    public static void main(String[] args) throws SQLException {
        createFakeEmployees(10);
        createFakeCostumers(5);
    }
    public static void createFakeCostumers(int amount) throws SQLException {
        CustomerStorageImpl customerStorage = new CustomerStorageImpl(conStr, user, pass);
        Faker faker = new Faker();
        for (int i = 0; i < amount; i++) {
            CustomerCreation creation = new CustomerCreation(faker.name().firstName(), faker.name().lastName(), faker.phoneNumber().subscriberNumber(8), faker.date().birthday());
            customerStorage.createCustomer(creation);
        }
    }

    public static void createFakeEmployees(int amount) throws SQLException {
        EmployeeStorageImpl employeeStorage = new EmployeeStorageImpl(conStr, user, pass);
        Faker faker = new Faker();
        for (int i = 0; i < amount; i++) {
            EmployeeCreation creation = new EmployeeCreation(faker.name().firstName(), faker.name().lastName());
            employeeStorage.createEmployee(creation);
        }
    }

}
