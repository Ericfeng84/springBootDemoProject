package com.example.springbootdemoproject.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.module.ModuleDescriptor;
import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
    private final  StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping()
    public List<Student> getStudents(){
        return studentService.getStudent();
    }

    @PostMapping()
    public Student registerNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
        return student;
    }

    @DeleteMapping(path = "{studentID}")
    public void deleteStudent(@PathVariable("studentID") long studentID){
        studentService.deleteByID(studentID);

    }
    @PutMapping(path = "{studentID}")
    public void updateStudent(@PathVariable("studentID") long studentID,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String email){
        studentService.updateByID(studentID,name,email);
    }

}
