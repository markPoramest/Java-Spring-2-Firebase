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
    @GetMapping
    public List getAllStudent() throws ExecutionException, InterruptedException {
        List data = firebaseService.showAllStudent();
        return  data;
    }

    @RequestMapping("/{id}")
    public Student getOnlyStudent(@PathVariable(value="id") String id) throws ExecutionException, InterruptedException {
        Student data = firebaseService.showOnlyStudent(id);
        return  data;
    }

    @DeleteMapping("/{id}")
    public boolean deleteStudent(@PathVariable(value="id") String id) throws ExecutionException, InterruptedException {
       boolean data = firebaseService.deleteStudent(id);
        return  data;
    }

    @PutMapping("/{id}")
    public boolean updateStudent(@PathVariable(value="id") String id,@RequestBody Student student) throws ExecutionException, InterruptedException {
        boolean data = firebaseService.updateStudent(id,student);
        return  data;
    }

}
