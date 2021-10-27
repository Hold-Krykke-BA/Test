package datalayer.customer;

import dto.Booking;
import dto.BookingCreation;

import java.sql.SQLException;
import java.util.Collection;

public class BookingStorageImpl implements BookingStorage {
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
