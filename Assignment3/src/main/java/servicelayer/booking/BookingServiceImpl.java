package servicelayer.booking;

import dto.Booking;
import dto.SmsMessage;

import java.util.Collection;
import java.util.Date;

public class BookingServiceImpl implements BookingService{
    @Override
    public int createBooking(int customerId, int employeeId, Date date, String start, String end) throws BookingServiceException {
        return 0;
    }

    @Override
    public Collection<Booking> getBookingsForCustomer(int customerId) throws BookingServiceException {
        return null;
    }

    @Override
    public Collection<Booking> getBookingsForEmployee(int employeeId) throws BookingServiceException {
        return null;
    }

    @Override
    public Booking getBookingById(int id) throws BookingServiceException {
        return null;
    }
}
