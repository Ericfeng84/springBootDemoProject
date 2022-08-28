package com.example.springbootdemoproject.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student>  getStudent(){

        return studentRepository.findAll();
    };

    public void addNewStudent(Student student) {
        System.out.println(student);
        Optional<Student> studentFindByEmail = studentRepository.findByEmail(student.getEmail());
        if(studentFindByEmail.isPresent()){
            throw new RuntimeException("email token");
        };
        studentRepository.save(student);
    }


    public void deleteByID(long studentID) {
        boolean idExist = studentRepository.existsById(studentID);
        if(!idExist){
            throw new RuntimeException("ID don't exist");
        }
        studentRepository.deleteById(studentID);
    }

    @Transactional
    public void updateByID(Student student) {
        System.out.println(student);
        Optional<Student> studentOptional = studentRepository.findById(student.getId());

        if(studentOptional.isPresent()){
            student.setName("EricUpdate");
            studentRepository.save(student);
        }
    }
    @Transactional
    public void updateByID(long studentID, String name, String email) {
        Student student = studentRepository.findById(studentID).
                orElseThrow(()-> new IllegalStateException("Id doesn't Exist"));
        if(name!= null &&
                name.length()>0 &&
                !Objects.equals(student.getName(),name))
        {
            student.setName(name);
        }
        System.out.println(email);
        if(email!= null &&
                email.length()>0 &&
                !Objects.equals(student.getEmail(),email))
        {
            Optional<Student> studentOptional = studentRepository.findByEmail(email);
            if(studentOptional.isPresent()){
                throw new RuntimeException("Email exist");
            }
            student.setEmail(email);
        }

    }
}
