package unit.servicelayer.booking;

import com.github.javafaker.Faker;
import datalayer.booking.BookingStorage;
import dto.SmsMessage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import servicelayer.booking.BookingService;
import servicelayer.booking.BookingServiceException;
import servicelayer.booking.BookingServiceImpl;

import java.sql.SQLException;
import java.util.Date;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("unit")
public class BookingTest {

    private BookingService bookingService;
    private BookingStorage storageMock;
    private Faker faker;

    @BeforeAll
    public void beforeAll(){
        storageMock = mock(BookingStorage.class);
        bookingService = new BookingServiceImpl(storageMock);
        faker = new Faker();
    }

    @Test
    public void TestCallStorageWhenCreatingBooking() throws BookingServiceException, SQLException {
        // Arrange
        int employeeID = 1;
        int customerID = 5;
        Date date = new Date();
        String start = "18:30";
        String end = "21:30";
        String recipient = faker.phoneNumber().subscriberNumber(8);
        String message = "Reservation confirmation on " + date + " between " + start + " and " + end;
        // Act
        bookingService.createBooking(customerID, employeeID, date, start, end, new SmsMessage(recipient, message));
        // Assert
        verify(storageMock, times(1))
                .createBooking(
                        argThat(x -> x.getCustomerId() == customerID &&
                                x.getEmployeeId() == employeeID));
    }
}
