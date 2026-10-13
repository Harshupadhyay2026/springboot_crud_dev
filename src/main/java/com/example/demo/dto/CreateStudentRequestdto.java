package com.example.demo.dto;

import jakarta.validation.constraints.*;

public class CreateStudentRequestdto {

   @NotBlank(message = "name must not be empty")
   @Size(min = 3,max = 50,message = "name should not too short or too large")
    private String name;

   @NotNull(message= "Age is required" )
   @Min(value = 18,message="student must be 18 years old")
    private Integer age;

@NotBlank(message = "email must not be blank")
   @Email(message="student message must be valid ")
    private String email;

   @NotNull(message="Roll no is required")
    private Integer rollNo;

   @NotBlank(message = "subject is required")
    private String subject;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
