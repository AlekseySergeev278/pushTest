package ru.sergeev.pushing;

import com.google.firebase.messaging.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class PushTestApplication {

    public static void main(String[] args) throws FirebaseMessagingException {
        SpringApplication.run(PushTestApplication.class, args);

        String registrationToken = "dnvgKCf8RjygZy-UBclFt2:APA91bHTtwR7XQklg_Xbh9O_alq7PvHnUt3a9c4DR73uNGB2dMoMBgagBCfYQYLyCtW0oHePKwFAQAT9duYJUUdglcDUC_RAg61tvDo1ASXbNlbJyJaka6fYLnvzDA8-Tw5ZVai6zfhf";

        Notification notification = Notification.builder()
                .setTitle("Server test")
                .setBody("Server body test")
                .build();

        Message message = Message.builder()
                .setNotification(notification)
                .putData("score", "850")
                .putData("time", "2:45")
                .setToken(registrationToken)
                .build();

        List<String> recipients = new ArrayList<>();
        recipients.add(registrationToken);
        recipients.add("registrationToken");
        MulticastMessage multicastMessage = MulticastMessage.builder()
                .setNotification(notification)
                .putData("score", "850")
                .putData("time", "2:45")
                .addAllTokens(recipients)
                .build();

        BatchResponse response = FirebaseMessaging.getInstance().sendMulticast(multicastMessage);
        System.out.println("Successfully sent message: " + response);

    }
}
