package com.kaweel.rookieworkshopspring.student;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Sql(value = {"/sql_script/student_data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/sql_script/subject_data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/sql_script/student_subject_data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/sql_script/student_cleanup.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Slf4j
public class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

    @Test
    @DisplayName("find all student found should total student should be '2'")
    public void found_findAll() {
        List<Student> students = studentRepository.findAll();
        Assertions.assertEquals(2, students.size());
    }

    @Test
    @DisplayName("find all student not found should total student should be '0'")
    public void not_found_findAll() {
        studentRepository.deleteAll();
        List<Student> students = studentRepository.findAll();
        Assertions.assertEquals(0, students.size());
    }

    @Test
    @DisplayName("find by student name is 'sephera' and grade is 'HIGH_SCHOOL' found")
    public void found_by_specification() {
        Optional<Student> student = studentRepository.findOne(
                Specification.where(StudentSpecification.nameIs("sephera")
                        .and(StudentSpecification.gradeIs(Grade.MIDDLE_SCHOOL)))
        );
        Assertions.assertTrue(student.isPresent());
        Assertions.assertEquals("sephera", student.get().getName());
        Assertions.assertEquals(Grade.MIDDLE_SCHOOL, student.get().getGrade());
    }

    @Test
    @DisplayName("find by student name is 'omen' and grade is 'HIGH_SCHOOL' found")
    public void found_by_criteria() {
        StudentCriteria studentCriteria = new StudentCriteria();
        studentCriteria.setName("omen");
        studentCriteria.setGrade(Grade.MIDDLE_SCHOOL);
        Optional<Student> student = studentRepository.findOne(StudentSpecification.findByCriteria(studentCriteria));
        Assertions.assertTrue(student.isPresent());
        Assertions.assertEquals("omen", student.get().getName());
        Assertions.assertEquals(Grade.MIDDLE_SCHOOL, student.get().getGrade());
    }

    @Test
    @DisplayName("find by student name is 'sephera' and grade is 'HIGH_SCHOOL' found using jql")
    public void found_by_jql() {
        List<StudentSubjectJQL> studentSubjectJQLS = studentRepository.findByNameJQL("sephera");
        Assertions.assertEquals(2, studentSubjectJQLS.size());
    }

    @Test
    @DisplayName("find by student name is 'sephera' and grade is 'HIGH_SCHOOL' found using native")
    public void found_by_native() {
        List<StudentSubjectNative> studentSubjectNatives = studentRepository.findByNameNative("sephera");
        Assertions.assertEquals(2, studentSubjectNatives.size());
    }

//    @Test
//    @DisplayName("delete by student name is 'sephera' total student should minus 1")
//    @Transactional
//    public void deleteByName_jql() {
//        int before = studentRepository.findAll().size();
//        studentRepository.deleteByNameJQL("sephera");
//        int after = studentRepository.findAll().size();
//        Assertions.assertEquals(before - 1, after);
//    }

    @Test
    @DisplayName("delete by student name is 'sephera' total student should minus 1")
    @Transactional
    public void deleteByName() {
        int before = studentRepository.findAll().size();
        studentRepository.deleteByName("sephera");
        int after = studentRepository.findAll().size();
        Assertions.assertEquals(before - 1, after);
    }

}
