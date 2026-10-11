package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.repository.Studentrepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class StudentService {
    Studentrepository studentrepository;
    StudentService(Studentrepository studentrepository){
        this.studentrepository = studentrepository;
    }
    public Student createStudent(Student studentReq) {

        Student studentresp = studentrepository.save(studentReq);

        return studentresp;}

        public Student getStudent(Long id){
           Optional<Student > studentResp = studentrepository.findById(id);
           if (studentResp.isPresent()) return studentResp.get();
           return null;
        }
    public List<Student> getAllStudent(){
        List<Student> studentlist = studentrepository.findAll();
        return studentlist;
    }
    public Student updateStudent(Long id, Student studentreq){
        Optional<Student > existingstudent = studentrepository.findById(id);
        if (existingstudent.isEmpty()){ return null;}

        Student studentTosave = existingstudent.get();

        studentTosave.setAge(studentreq.getAge());
        studentTosave.setEmail(studentreq.getEmail());
        studentTosave.setName(studentreq.getName());
        studentTosave.setSubject(studentreq.getSubject());
        studentTosave.setRollNo(studentreq.getRollNo());

        return studentrepository.save(studentTosave);
    }
    public boolean deletestudent (Long id){
       Boolean isStudent =  studentrepository.existsById(id);

       if(!isStudent) return false;

       studentrepository.deleteById(id);
       return true;


    }
}




