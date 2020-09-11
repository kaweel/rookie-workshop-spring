package com.kaweel.rookieworkshopspring.student;

import lombok.Data;

@Data
public class StudentSubjectJQL {
    private Integer id;
    private String studentName;
    private Grade grade;
    private Integer subjectID;
    private String subjectName;

    public StudentSubjectJQL(Integer id, String studentName, Grade grade, Integer subjectID, String subjectName) {
        this.id = id;
        this.studentName = studentName;
        this.grade = grade;
        this.subjectID = subjectID;
        this.subjectName = subjectName;
    }
}
