package com.example.JavaSpring2Firebase.Service;

import com.example.JavaSpring2Firebase.Model.Student;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firestore.v1.WriteResult;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class FirebaseService {
    private Firestore db;

    public FirebaseService() throws IOException {
       initDB();
    }

    public boolean addStudent(Student student){
        String id = UUID.randomUUID().toString();
        CollectionReference collectionReference = db.collection("Student");
        Map<String, Student> data = new HashMap<>();
        data.put(id,student);
        collectionReference.add(data);
        return true;
    }

    private void initDB() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream serviceAccount = classLoader.getResourceAsStream("javaspring-2-firebase-firebase-sdk.json");
        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(credentials)
                .build();
        FirebaseApp.initializeApp(options);
        this.db = FirestoreClient.getFirestore();
    }
}
