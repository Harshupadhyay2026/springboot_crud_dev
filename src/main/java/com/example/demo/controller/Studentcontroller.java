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
    @PostMapping("/create")
    public ResponseEntity<CreateStudentResponsedto> createStudent(
          @Valid @RequestBody
            CreateStudentRequestdto studentRequestdto){

        CreateStudentResponsedto createdStudent =  studentService.createStudent(studentRequestdto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdStudent);}
//read one
        @GetMapping("/get")
        public ResponseEntity<CreateStudentResponsedto> getStudent(@RequestParam Long id){
            CreateStudentResponsedto studentresp = studentService.getStudent(id);

            if (studentresp == null) return ResponseEntity.notFound().build();
            return ResponseEntity.ok(studentresp);
        }
        // read all
        @GetMapping("/getAll")
        public ResponseEntity<List<CreateStudentResponsedto>> getAllStudent(){
            List<CreateStudentResponsedto> studentlost = studentService.getAllStudent();

            if (studentlost.isEmpty()) return ResponseEntity.notFound().build();
            return ResponseEntity.ok(studentlost);
        }

        // update by id
    @PutMapping("/update")
    public ResponseEntity<UpdateStudentResponsedto> updateStudent(@RequestParam Long id,            // ?id = value
                                                                  @RequestBody UpdateStudentRequestdto studentReq){
       UpdateStudentResponsedto studentresp = studentService.updateStudent(id,studentReq);

        if (studentresp == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(studentresp);
    }
    //delete by id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletestudent(@PathVariable Long id ) {    // "/id"
      Boolean isdeleted = studentService.deletestudent(id);

      if (!isdeleted){
          return ResponseEntity.notFound().build();
      }
      return ResponseEntity.ok("records deleted");
    }
    //deletesoftly

    @PatchMapping("/delete-soft/{id}")
    public ResponseEntity<String> deleteStudentSoftly(@PathVariable Long id) {
        Boolean isdeleted =
                studentService.deleteStudentSoftly(id);

        if (!isdeleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity
                .ok("record deleted");
    }
    }


