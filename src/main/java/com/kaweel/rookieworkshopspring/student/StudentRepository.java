package com.kaweel.rookieworkshopspring.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer>, JpaSpecificationExecutor {

    Optional<Student> findByName(String name);

    Optional<Student> findByStudentID(Integer studentID);

    Optional<Student> findByNameAndPassword(String name, String password);

    @Query(
            value = "SELECT \n" +
                    "new com.kaweel.rookieworkshopspring.student.StudentSubjectJQL(\n" +
                    "s.id,\n" +
                    "s.name,\n" +
                    "s.grade,\n" +
                    "su.id,\n" +
                    "su.name\n" +
                    ")\n" +
                    "FROM Student s\n" +
                    "JOIN s.studentSubjects ss\n" +
                    "JOIN ss.subject su\n" +
                    "WHERE s.name = :name"
    )
    List<StudentSubjectJQL> findByNameJQL(@Param("name") String name);

    @Query(

            value = "SELECT \n" +
                    "s.id as studentId,\n" +
                    "s.name as studentName,\n" +
                    "s.grade as grade,\n" +
                    "su.id as subjectId,\n" +
                    "su.name as subjectName\n" +
                    "FROM student s\n" +
                    "LEFT JOIN student_subject ss ON s.id = ss.student_id\n" +
                    "LEFT JOIN subject su ON ss.subject_id = su.id\n" +
                    "WHERE s.name = ?1",
            nativeQuery = true
    )
    List<StudentSubjectNative> findByNameNative(String name);

    void deleteByName(String name);

    @Modifying
    @Query(
            value = "DELETE FROM Student s WHERE s.name = :name"
    )
    void deleteByNameJQL(@Param("name") String name);

}
