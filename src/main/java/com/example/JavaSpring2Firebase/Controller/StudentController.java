package com.example.JavaSpring2Firebase.Controller;

import com.example.JavaSpring2Firebase.Model.Student;
import com.example.JavaSpring2Firebase.Service.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
