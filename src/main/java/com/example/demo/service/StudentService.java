package com.example.demo.service;

import com.example.demo.dto.CreateStudentRequestdto;
import com.example.demo.dto.CreateStudentResponsedto;
import com.example.demo.dto.UpdateStudentRequestdto;
import com.example.demo.dto.UpdateStudentResponsedto;
import com.example.demo.entity.Student;
import com.example.demo.exception.DuplicateREsourceException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.Studentrepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    Studentrepository studentrepository;
    StudentService(Studentrepository studentrepository){
        this.studentrepository = studentrepository;
    }


    public CreateStudentResponsedto createStudent(CreateStudentRequestdto studentReqdto) {
        Student student = mapToEntity(studentReqdto);
       if (emailExists(student)){
    throw new DuplicateREsourceException("Student with email" + student.getEmail() + "already exists");
}

        try {
            Thread.sleep(3000);
        }
        catch (Exception e){}

        Student studentResp = studentrepository.save(student);


        return mapToDto(studentResp);}



      public CreateStudentResponsedto getStudent(Long id){
          Student studentResp = studentrepository
                  .findByIdAndDeletedIsFalse(id).orElseThrow(() ->
                                                   new ResourceNotFoundException("Student with id" + id + "not found"));
                    return mapToDto(studentResp);

//           if (studentResp.isPresent()) return mapToDto(studentResp.get());
//
//           return null;
        }



    public List<CreateStudentResponsedto> getAllStudent(){
        List<Student> studentlist = studentrepository.findByDeletedIsFalse();
        return studentlist.stream().map(this::mapToDto).toList();
    }


    public UpdateStudentResponsedto updateStudent(Long id, UpdateStudentRequestdto studentreq){
        Student existingstudent = studentrepository
                .findByIdAndDeletedIsFalse(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student with id" + id + "not found"));

        existingstudent.setAge(studentreq.getAge());

        existingstudent.setName(studentreq.getName());
        existingstudent.setSubject(studentreq.getSubject());
        existingstudent.setRollNo(studentreq.getRollNo());
        existingstudent.setUpdateAt(LocalDateTime.now());
        existingstudent.setDeleted(false);

         Student savedstudent = studentrepository.save(existingstudent);
         return mapToUpdateDto(savedstudent);
    }

    public void deletestudent (Long id){
       Student studentToBeDeleted =  studentrepository
               .findById(id)
               .orElseThrow(() ->
                new ResourceNotFoundException("Student with id" + id + "not found"));


       studentrepository.delete(studentToBeDeleted);

    }


    public void deleteStudentSoftly(Long id) {
        Student studentToBeDeleted =  studentrepository
                .findByIdAndDeletedIsFalse(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student with id" + id + "not found"));

        studentToBeDeleted.setDeleted(true);
        studentrepository.save(studentToBeDeleted);}


   private Student  mapToEntity(CreateStudentRequestdto studentRequestdto){
        Student student = new Student();

        student.setName(studentRequestdto.getName());
        student.setRollNo(studentRequestdto.getRollNo());
        student.setSubject(studentRequestdto.getSubject());
        student.setEmail(studentRequestdto.getEmail());
        student.setAge(studentRequestdto.getAge());
        student.setDeleted(false);
        student.setCreateAt(LocalDateTime.now());
        student.setUpdateAt(LocalDateTime.now());
        return student;
   }
   private CreateStudentResponsedto mapToDto(Student student){
        CreateStudentResponsedto studentResponse =new CreateStudentResponsedto();

        studentResponse.setName(student.getName());
        studentResponse.setAge(student.getAge());
        studentResponse.setEmail(student.getEmail());
        studentResponse.setId(student.getId());
        studentResponse.setRollNo(student.getRollNo());
        studentResponse.setSubject(student.getSubject());
        studentResponse.setMessage("you have been saved successfully ");
        studentResponse.setCreateAt(student.getCreateAt());
        studentResponse.setUpdateAt(student.getUpdateAt());

        return studentResponse;}

        private UpdateStudentResponsedto mapToUpdateDto(Student student){

           UpdateStudentResponsedto studentResponse =new UpdateStudentResponsedto();

           studentResponse.setName(student.getName());
           studentResponse.setAge(student.getAge());

           studentResponse.setId(student.getId());
           studentResponse.setRollNo(student.getRollNo());
           studentResponse.setSubject(student.getSubject());


           studentResponse.setUpdateAt(student.getUpdateAt());

           return studentResponse;

       }
       private boolean emailExists(Student student){
       return studentrepository.existsByEmail(student.getEmail());
       }


   }








