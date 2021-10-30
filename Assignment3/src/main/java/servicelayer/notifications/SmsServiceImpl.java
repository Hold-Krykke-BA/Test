package servicelayer.notifications;

import dto.SmsMessage;

public class SmsServiceImpl implements SmsService{
    @Override
    public boolean sendSms(SmsMessage message) {
        return message != null && !message.getMessage().isEmpty() && !message.getRecipient().isEmpty();
    }
}
