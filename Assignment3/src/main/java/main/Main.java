package main;

import dto.Customer;
import datalayer.customer.CustomerStorage;

import java.sql.SQLException;

public class Main {

    private static final String conStr = "jdbc:mysql://localhost:3306/DemoApplication";
    private static final String user = "root";
    private static final String pass = "schmeep7";

    public static void main(String[] args) throws SQLException {
        CustomerStorage storage = new CustomerStorage(conStr, user, pass);

        System.out.println("Got customers: ");
        for(Customer c : storage.getCustomers()) {
            System.out.println(toString(c));
        }
        System.out.println("The end.");
    }

    public static String toString(Customer c) {
        return "{" + c.getId() + ", " + c.getFirstname() + ", " + c.getLastname() + "}";
    }
}
