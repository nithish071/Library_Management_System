package com.nithish.library_management_system.Controllers;

import com.nithish.library_management_system.Model.Student;
import com.nithish.library_management_system.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @PostMapping("/add")
    public String addStudent(@RequestBody Student student){
        String result = studentService.addStudent(student);
        return result;
    }

    @GetMapping("getAllStudents")
    public List<Student> getAllStudent(){
        return studentService.getAllStudent();
    }
}
