package datalayer.booking;

import dto.Booking;
import dto.BookingCreation;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class BookingStorageImpl implements BookingStorage {
    private final String connectionString;
    private final String username;
    private final String password;

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
        String sql = "insert into Bookings(customerId,employeeId,date,start,end) values (?,?,?,?,?)";
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, bookingCreation.getCustomerId());
            stmt.setInt(2, bookingCreation.getEmployeeId());
            stmt.setDate(3, (Date) bookingCreation.getDate());
            stmt.setString(4, bookingCreation.getStart());
            stmt.setString(5, bookingCreation.getEnd());
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
    public Collection<Booking> getBookingsForCustomer(int customerId) throws SQLException {
        String sql = "select ID, customerId, employeeId,date,start,end from Bookings where customerId= ?";
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            ArrayList<Booking> results = new ArrayList<>();
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                int employeeId = resultSet.getInt("employeeId");
                Date date = resultSet.getDate("date");
                Time start = resultSet.getTime("start");
                Time end = resultSet.getTime("end");
                Booking c = new Booking(id, customerId, employeeId, date, start, end);
                results.add(c);
            }
            return results;
        }
    }

    @Override
    public Collection<Booking> getBookingsForEmployee(int employeeId) throws SQLException {
        String sql = "select ID, customerId, employeeId,date,start,end from Bookings where customerId= ?";
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, employeeId);
            ArrayList<Booking> results = new ArrayList<>();
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                int customerId = resultSet.getInt("customerId");
                Date date = resultSet.getDate("date");
                Time start = resultSet.getTime("start");
                Time end = resultSet.getTime("end");
                Booking c = new Booking(id, customerId, employeeId, date, start, end);
                results.add(c);
            }
            return results;
        }
    }

    @Override
    public Booking getBookingById(int bookingId) throws SQLException{
        String sql = "select * from Bookings where id = ?";
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, bookingId);

            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()){
                    int id = resultSet.getInt("ID");
                    int customerId = resultSet.getInt("customerId");
                    int employeeId = resultSet.getInt("employeeId");
                    Date date = resultSet.getDate("date");
                    Time start = resultSet.getTime("start");
                    Time end = resultSet.getTime("end");
                    return new Booking(id, customerId, employeeId, date, start, end);
                }
                return null;
            }
        }
    }
}
