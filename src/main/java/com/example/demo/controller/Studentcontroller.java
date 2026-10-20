package com.example.demo.controller;
import com.example.demo.dto.CreateStudentRequestdto;
import com.example.demo.dto.CreateStudentResponsedto;
import com.example.demo.dto.UpdateStudentRequestdto;
import com.example.demo.dto.UpdateStudentResponsedto;
import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class Studentcontroller{

private StudentService studentService;

public Studentcontroller(StudentService studentService){
    this.studentService = studentService;
    }

 //create student
    @PostMapping
    public ResponseEntity<CreateStudentResponsedto> createStudent(
          @Valid @RequestBody
            CreateStudentRequestdto studentRequestdto){

        CreateStudentResponsedto createdStudent =  studentService.createStudent(studentRequestdto);
        System.out.println("student created");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdStudent);}

//read one
        @GetMapping("/{id}")
        public ResponseEntity<CreateStudentResponsedto> getStudent(@PathVariable Long id){
            CreateStudentResponsedto studentresp = studentService.getStudent(id);

            return ResponseEntity.ok(studentresp);
        }

        // read all
        @GetMapping
        public ResponseEntity<List<CreateStudentResponsedto>> getAllStudent(){
            List<CreateStudentResponsedto> studentlost = studentService.getAllStudent();

            return ResponseEntity.ok(studentlost);
        }

        // update by id
    @PutMapping
    public ResponseEntity<UpdateStudentResponsedto> updateStudent(@RequestParam Long id,            // ?id = value
                                                                  @RequestBody UpdateStudentRequestdto studentReq){
       UpdateStudentResponsedto studentresp = studentService.updateStudent(id,studentReq);
        return ResponseEntity.ok(studentresp);
    }
    //delete by id
    @DeleteMapping
    public ResponseEntity<String> deletestudent(@PathVariable Long id ) {    // "/id"
       studentService.deletestudent(id);
      return ResponseEntity.noContent().build();
    }
    //deletesoftly

    @PatchMapping("/delete-soft/{id}")
    public ResponseEntity<String> deleteStudentSoftly(@PathVariable Long id) {

                studentService.deleteStudentSoftly(id);
               return ResponseEntity
                .noContent().build();
    }
    }


