package com.kaweel.rookieworkshopspring.student;

import com.kaweel.rookieworkshopspring.config.handle.CustomException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @InjectMocks
    StudentService studentService;

    @Mock
    StudentRepository studentRepository;

    @Test
    public void findById_not_found() {
        BDDMockito.given(studentRepository.findById(BDDMockito.anyInt())).willReturn(Optional.empty());
        Assertions.assertThrows(CustomException.class, () -> studentService.findById(1));
    }

    @Test
    public void findById_found() {
        BDDMockito.given(studentRepository.findById(BDDMockito.anyInt())).willReturn(Optional.of(new Student()));
        StudentService.Student student = studentService.findById(1);
        Assertions.assertNotNull(student);
    }

    @Test
    public void findAll_not_found() {
        BDDMockito.given(studentRepository.findAll()).willReturn(Arrays.asList());
        Assertions.assertThrows(CustomException.class, () -> studentService.findAll());
    }

    @Test
    public void findAll_found() {
        BDDMockito.given(studentRepository.findAll()).willReturn(Arrays.asList(new Student()));
        List<StudentService.Student> all = studentService.findAll();
        Assertions.assertEquals(1, all.size());
    }

    @Test
    public void save_duplicate() {
        //non stub
        BDDMockito.given(studentRepository.findByStudentID(BDDMockito.anyInt())).willReturn(Optional.of(new Student()));
        Assertions.assertThrows(CustomException.class, () -> studentService.save(new StudentService.Student().setStudentID(101)));
        BDDMockito.verify(studentRepository, BDDMockito.times(1)).findByStudentID(BDDMockito.anyInt());
    }

    @Test
    public void save() {
        StudentService.Student student = new StudentService.Student()
                .setStudentID(103);
        BDDMockito.given(studentRepository.findByStudentID(BDDMockito.anyInt())).willReturn(Optional.empty());
        studentService.save(student);
        BDDMockito.verify(studentRepository, BDDMockito.times(1)).findByStudentID(BDDMockito.anyInt());
    }
}
