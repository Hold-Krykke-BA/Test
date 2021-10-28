package datalayer.booking;

import dto.Booking;
import dto.BookingCreation;

import java.sql.*;
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
        var sql = "insert into Bookings(customerId,employeeId,date,start,end) values (?,?,?,?,?)";
        try (var con = getConnection();
             var stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, bookingCreation.getCustomerId());
            stmt.setInt(2, bookingCreation.getEmployeeId());
            stmt.setDate(3, (Date) bookingCreation.getDate());
            stmt.setString(4, bookingCreation.getStart());
            stmt.setString(5, bookingCreation.getEnd());
            stmt.executeUpdate();
            // get the newly created id
            try (var resultSet = stmt.getGeneratedKeys()) {
                resultSet.next();
                int newId = resultSet.getInt(1);
                return newId;
            }
        }
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
