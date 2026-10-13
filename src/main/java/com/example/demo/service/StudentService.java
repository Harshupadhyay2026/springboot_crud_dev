package com.example.demo.service;

import com.example.demo.dto.CreateStudentRequestdto;
import com.example.demo.dto.CreateStudentResponsedto;
import com.example.demo.dto.UpdateStudentRequestdto;
import com.example.demo.dto.UpdateStudentResponsedto;
import com.example.demo.entity.Student;
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
        Student studentResp = studentrepository.save(student);
        return mapToDto(studentResp);}



      public CreateStudentResponsedto getStudent(Long id){
          Optional<Student> studentResp = studentrepository.findByIdAndDeletedIsFalse(id);
           if (studentResp.isPresent()) return mapToDto(studentResp.get());

           return null;
        }



    public List<CreateStudentResponsedto> getAllStudent(){
        List<Student> studentlist = studentrepository.findByDeletedIsFalse();
        return studentlist.stream().map(this::mapToDto).toList();
    }


    public UpdateStudentResponsedto updateStudent(Long id, UpdateStudentRequestdto studentreq){
        Optional<Student > existingstudent = studentrepository.findById(id);

        if (existingstudent.isEmpty()){ return null;}

        Student studentTosave = existingstudent.get();

        studentTosave.setAge(studentreq.getAge());

        studentTosave.setName(studentreq.getName());
        studentTosave.setSubject(studentreq.getSubject());
        studentTosave.setRollNo(studentreq.getRollNo());
        studentTosave.setUpdateAt(LocalDateTime.now());
        studentTosave.setDeleted(false);

         Student savedstudent = studentrepository.save(studentTosave);
         return mapToUpdateDto(savedstudent);
    }

    public boolean deletestudent (Long id){
       Boolean isStudent =  studentrepository.existsById(id);

       if(!isStudent) return false;

       studentrepository.deleteById(id);
       return true;
    }


    public Boolean deleteStudentSoftly(Long id) {
        Optional<Student> existingstudent = studentrepository.findByIdAndDeletedIsFalse(id);

        if (existingstudent.isEmpty()) return false;

        Student studentTosave = existingstudent.get();
        studentTosave.setDeleted(true);
        studentrepository.save(studentTosave);
        return true;
    }
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


   }








