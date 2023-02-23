package ru.sergeev.pushing.conf;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.context.annotation.Bean;

import java.io.FileInputStream;
import java.io.IOException;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    FirebaseMessaging firebaseMessaging(FirebaseApp firebaseApp) {

        return FirebaseMessaging.getInstance(firebaseApp);
    }

    @Bean
    FirebaseApp firebaseApp(FirebaseOptions options) {
        return FirebaseApp.initializeApp(options);
    }

    @Bean
    FirebaseOptions firebaseOptions() throws IOException {
        final FileInputStream serviceAccount =
                new FileInputStream("./src/main/resources/serviceAccountKey.json");

        return new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();
    }
}
