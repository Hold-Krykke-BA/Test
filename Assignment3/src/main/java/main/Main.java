package main;

import com.github.javafaker.Faker;
import datalayer.booking.BookingStorageImpl;
import datalayer.employee.EmployeeStorageImpl;
import datalayer.customer.CustomerStorageImpl;
import dto.EmployeeCreation;

import java.sql.SQLException;

public class Main {

    private static final String conStr = "jdbc:mysql://localhost:3307/BookingSystem";
    private static final String user = "root";
    private static final String pass = "holdkrykke";

    public static void main(String[] args) throws SQLException {
        CustomerStorageImpl storage = new CustomerStorageImpl(conStr, user, pass);
        EmployeeStorageImpl eStorage = new EmployeeStorageImpl(conStr, user, pass);
        BookingStorageImpl bStorage = new BookingStorageImpl(conStr, user, pass);
        createFakeEmployees(10);

    }

    public static void createFakeEmployees(int amount) throws SQLException {
        EmployeeStorageImpl eStorage = new EmployeeStorageImpl(conStr, user, pass);
        Faker faker = new Faker();
        for (int i = 0; i < amount; i++) {
            EmployeeCreation c = new EmployeeCreation(faker.name().firstName(), faker.name().lastName());
            eStorage.createEmployee(c);
        }
    }

}
