package com.kalela.InnovexSolutions.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;


@Service
public class FirebaseInitializer {

    @PostConstruct
    private void initFirebase() {
        try {
            String serviceAccountJson = massageWhitespace(System.getenv("SERVICE_ACCOUNT_JSON"));
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(new ByteArrayInputStream(serviceAccountJson.getBytes())))
                    .setDatabaseUrl("https://innovex-supervisor.firebaseio.com")
                    .build();
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static String massageWhitespace(String s) {
        String newString = "";
        for (Character c : s.toCharArray()) {
            if ("00a0".equals(Integer.toHexString(c | 0x10000).substring(1))) {
                newString += " ";
            } else {
                newString += c;
            }
        }
        return newString;
    }

    public Firestore getFirestoreDb() {
        return FirestoreClient.getFirestore();
    }
}
