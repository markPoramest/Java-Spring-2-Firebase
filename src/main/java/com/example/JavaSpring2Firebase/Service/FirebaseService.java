package com.example.JavaSpring2Firebase.Service;

import com.example.JavaSpring2Firebase.Model.Student;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firestore.v1.WriteResult;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class FirebaseService {
    private Firestore db;

    public FirebaseService() throws IOException {
       initDB();
    }

    public boolean addStudent(Student student){
        String id = UUID.randomUUID().toString();
        ArrayList<Student> arrayList = new ArrayList<>();
        CollectionReference collectionReference = db.collection("Student");
        Map<String, Student> data = new HashMap<>();
        data.put(id,student);
        collectionReference.add(data);
        return true;
    }

    public List showAllStudent() throws ExecutionException, InterruptedException {
        CollectionReference collectionReference = db.collection("Student");
        ApiFuture<QuerySnapshot> future = collectionReference.get();
        QuerySnapshot document = future.get();
        List<Student> student = null;
        if(!document.isEmpty()) {
            student = document.toObjects(Student.class);
            return student;
        }else {
            return null;
        }
    }

    public List<Student> showOnlyStudent() throws ExecutionException, InterruptedException {
        List<Student> student = new ArrayList<>();
        Iterable<DocumentReference> documentReference = db.collection("Student").listDocuments();
        for(DocumentReference documentReference1 : documentReference){
            ApiFuture<DocumentSnapshot> future = documentReference1.get();
            DocumentSnapshot document = future.get();
            if (document.exists()) {
                student.add(document.toObject(Student.class));
            }
        }
        return student;
    }

    private void initDB() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream serviceAccount = classLoader.getResourceAsStream("javaspring-2-firebase-firebase-sdk.json");
        assert serviceAccount != null;
        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(credentials)
                .build();
        FirebaseApp.initializeApp(options);
        this.db = FirestoreClient.getFirestore();
    }
}
