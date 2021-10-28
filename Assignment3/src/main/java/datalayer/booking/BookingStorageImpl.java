package datalayer.booking;

import dto.Booking;
import dto.BookingCreation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;

public class BookingStorageImpl implements BookingStorage {
    private String connectionString;
    private String username, password;

    public BookingStorageImpl(String conStr, String user, String pass){
        connectionString = conStr;
        username = user;
        password = pass;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connectionString, username, password);
    }
    
    @Override
    public int createBooking(BookingCreation bookingCreation) throws SQLException {
        return 0;
    }

    @Override
    public Collection<Booking> getBookingsForCustomer(int customerId) throws SQLException {
        return null;
    }

    @Override
    public Collection<Booking> getBookingsForEmployee(int employeeId) throws SQLException {
        return null;
    }

    @Override
    public Booking getBookingById(int bookingId) throws SQLException {
        return null;
    }
}
