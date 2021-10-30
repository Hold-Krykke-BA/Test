package servicelayer.booking;

import datalayer.booking.BookingStorage;
import dto.Booking;
import dto.BookingCreation;
import dto.SmsMessage;
import servicelayer.customer.CustomerServiceException;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

public class BookingServiceImpl implements BookingService{
    private BookingStorage bookingStorage;

    public BookingServiceImpl(BookingStorage bookingStorage) {
        this.bookingStorage = bookingStorage;
    }

    @Override
    public int createBooking(int customerId, int employeeId, Date date, String start, String end) throws BookingServiceException {
        try {
            return bookingStorage.createBooking(new BookingCreation(customerId, employeeId, date, start, end));
        } catch (SQLException e) {
            throw new BookingServiceException(e.getMessage());
        }
    }

    @Override
    public Collection<Booking> getBookingsForCustomer(int customerId) throws BookingServiceException {
        try {
            return bookingStorage.getBookingsForCustomer(customerId);
        } catch (SQLException e) {
            throw new BookingServiceException(e.getMessage());
        }
    }

    @Override
    public Collection<Booking> getBookingsForEmployee(int employeeId) throws BookingServiceException {
        try {
            return bookingStorage.getBookingsForEmployee(employeeId);
        } catch (SQLException e) {
            throw new BookingServiceException(e.getMessage());
        }
    }

    @Override
    public Booking getBookingById(int id) throws BookingServiceException {
        try {
            return bookingStorage.getBookingById(id);
        } catch (SQLException e) {
            throw new BookingServiceException(e.getMessage());
        }
    }
}
