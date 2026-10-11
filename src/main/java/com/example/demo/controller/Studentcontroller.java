package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/api/students")
public class Studentcontroller{

private StudentService studentService;

public Studentcontroller(StudentService studentService){
    this.studentService = studentService;
    }

 //create student
    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student){

      Student createdStudent =  studentService.createStudent(student);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdStudent);}
//read one
        @GetMapping("/get/{id}")
        public ResponseEntity<Student> getStudent(@PathVariable Long id){
            Student studentresp = studentService.getStudent(id);

            if (studentresp == null) return ResponseEntity.notFound().build();
            return ResponseEntity.ok(studentresp);
        }
        // read all
        @GetMapping("/getAll")
        public ResponseEntity<List<Student>> getAllStudent(){
            List<Student> studentlost = studentService.getAllStudent();

            if (studentlost.isEmpty()) return ResponseEntity.notFound().build();
            return ResponseEntity.ok(studentlost);
        }

        // update by id
    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id,
                                                 @RequestBody Student studentreq){
        Student studentresp = studentService.updateStudent(id,studentreq);

        if (studentresp == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(studentresp);
    }
    //delete by id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletestudent(@PathVariable Long id ){
      Boolean isdeleted = studentService.deletestudent(id);

      if (!isdeleted){
          return ResponseEntity.notFound().build();
      }
      return ResponseEntity.ok("records deleted");
    }

    }


