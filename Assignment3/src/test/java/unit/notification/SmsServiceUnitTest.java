package unit.notification;

import dto.SmsMessage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import servicelayer.notifications.SmsService;
import servicelayer.notifications.SmsServiceImpl;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("unit")
public class SmsServiceUnitTest {
    private SmsService smsService;

    @BeforeAll
    public void beforeAll(){
        smsService = new SmsServiceImpl();
    }

    @Test
    public void TestSendMessage()  {
        // Arrange
        String phone = "12345678";
        String message = "Test message";
        // Act
        boolean sms = smsService.sendSms(new SmsMessage(phone, message));
        // Assert
        assertTrue(sms);
    }

    @Test
    public void TestSendMessageWithNoMessage() {
        // Arrange
        String phone = "";
        String message = "Test message";
        // Act
        boolean sms = smsService.sendSms(new SmsMessage(phone, message));
        // Assert
        assertFalse(sms);
    }

    @Test
    public void TestSendMessageWithNoReceiver() {
        // Arrange
        String phone = "";
        String message = "Test message";
        // Act
        boolean sms = smsService.sendSms(new SmsMessage(phone, message));
        // Assert
        assertFalse(sms);
    }

    @Test
    public void TestSendMessageWithNoReceiverOrMessage() {
        // Arrange
        String phone = "";
        String message = "";
        // Act
        boolean sms = smsService.sendSms(new SmsMessage(phone, message));
        // Assert
        assertFalse(sms);
    }
}