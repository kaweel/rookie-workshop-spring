package com.kaweel.rookieworkshopspring;

import com.kaweel.rookieworkshopspring.student.Grade;
import com.kaweel.rookieworkshopspring.student.Student;
import com.kaweel.rookieworkshopspring.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class RookieWorkshopSpringApplication {

    @Autowired
    StudentRepository studentRepository;

    public static void main(String[] args) {
        SpringApplication.run(RookieWorkshopSpringApplication.class, args);
    }

    @PostConstruct
    public void inti() {
        Student student = new Student();
        student.setStudentID(101);
        student.setName("s");
        student.setPassword("s");
        student.setGrade(Grade.MIDDLE_SCHOOL);
        studentRepository.save(student);
    }
}
