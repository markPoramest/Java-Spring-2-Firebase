package com.example.JavaSpring2Firebase.Controller;

import com.example.JavaSpring2Firebase.Model.Student;
import com.example.JavaSpring2Firebase.Service.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    FirebaseService firebaseService;

    @PostMapping("/add")
    public boolean addStudent(@RequestBody Student student){
        boolean data = firebaseService.addStudent(student);
        return  data;
    }
    /*@GetMapping
    public List getAllStudent() throws ExecutionException, InterruptedException {
        List data = firebaseService.showAllStudent();
        return  data;
    }*/

    @GetMapping
    public List<Student> getOnlyStudent() throws ExecutionException, InterruptedException {
        List<Student> data = firebaseService.showOnlyStudent();
        return  data;
    }

}
