package com.kaweel.rookieworkshopspring.student;

import com.kaweel.rookieworkshopspring.config.handle.CustomException;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentService(
            StudentRepository studentRepository
    ) {
        this.studentRepository = studentRepository;
    }

    @Data
    @Accessors(chain = true)
    public static class Student {
        private Integer id;
        private Integer studentID;
        private String name;
        private Grade grade;
        private String password;
    }

    private static com.kaweel.rookieworkshopspring.student.Student transformStudent(Student s) {
        com.kaweel.rookieworkshopspring.student.Student student = new com.kaweel.rookieworkshopspring.student.Student();
        BeanUtils.copyProperties(s, student, "id");
        return student;
    }

    private Student transformStudent(com.kaweel.rookieworkshopspring.student.Student s) {
        Student student = new Student();
        BeanUtils.copyProperties(s, student);
        return student;
    }

    public List<Student> findAll() {
        List<Student> students = studentRepository.findAll()
                .stream()
                .map(i -> transformStudent(i))
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(students)) {
            throw new CustomException(HttpStatus.NOT_FOUND);
        }
        return students;
    }

    public Student findById(Integer id) {
        return studentRepository.findById(id)
                .map(i -> transformStudent(i))
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND));
    }

    @Transactional
    public void save(Student request) {
        studentRepository.findByStudentID(request.getStudentID())
                .ifPresentOrElse(
                        i -> {
                            throw new CustomException(HttpStatus.CONFLICT);
                        }, () -> {
                            com.kaweel.rookieworkshopspring.student.Student student = transformStudent(request);
                            studentRepository.save(student);
                        }
                );
    }

    @Transactional
    public void update(Student request) {
        studentRepository.findByStudentID(request.getStudentID())
                .ifPresentOrElse(
                        student -> {
                            BeanUtils.copyProperties(request, student, "id");
                            studentRepository.save(student);
                        }, () -> {
                            throw new CustomException(HttpStatus.NOT_FOUND);
                        }
                );
    }

    @Transactional
    public void delete(Integer studentID) {
        studentRepository.findByStudentID(studentID)
                .ifPresentOrElse(
                        student -> {
                            studentRepository.delete(student);
                        }, () -> {
                            throw new CustomException(HttpStatus.NOT_FOUND);
                        }
                );
    }

}
