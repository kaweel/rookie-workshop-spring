package com.kaweel.rookieworkshopspring.student;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/student")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("")
    public List<StudentService.Student> findAll() {
        return studentService.findAll();
    }

    @GetMapping("/{studentID}")
    public void findByStudentID(@PathVariable Integer studentID) {
        studentService.findById(studentID);
    }

    @PostMapping("")
    public void save(@RequestBody StudentService.Student student) {
        studentService.save(student);
    }

    @PutMapping("")
    public void update(@RequestBody StudentService.Student student) {
        studentService.update(student);
    }

    @DeleteMapping("/{studentID}")
    public void delete(@PathVariable Integer studentID) {
        studentService.delete(studentID);
    }


}
