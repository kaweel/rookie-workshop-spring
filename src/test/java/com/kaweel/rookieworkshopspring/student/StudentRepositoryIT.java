package com.kaweel.rookieworkshopspring.student;

import com.kaweel.rookieworkshopspring.RookieWorkshopSpringApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = RookieWorkshopSpringApplication.class)
@Tag("integration")
public class StudentRepositoryIT {

    @Autowired
    StudentRepository studentRepository;

    @Test
    @DisplayName("find all student found should total student should '2'")
    public void found_findAll() {
        List<Student> students = studentRepository.findAll();
        Assertions.assertEquals(0, students.size());
    }

}
