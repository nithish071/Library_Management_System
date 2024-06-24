package com.nithish.library_management_system.Services;

import com.nithish.library_management_system.Model.Student;
import com.nithish.library_management_system.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public String addStudent(Student student){

        studentRepository.save(student);

        return "Student has been saved to the DB";
    }

    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }
}
