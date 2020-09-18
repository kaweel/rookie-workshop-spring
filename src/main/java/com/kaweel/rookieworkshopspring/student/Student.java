package com.kaweel.rookieworkshopspring.student;

import com.kaweel.rookieworkshopspring.entity.ActionEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(
        name = "student",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"student_id"}
                )
        })
@EqualsAndHashCode(callSuper = false)
public class Student extends ActionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "student_id", nullable = false)
    private Integer studentID;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "grade", nullable = false)
    @Enumerated(EnumType.STRING)
    private Grade grade;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<StudentSubject> studentSubjects;
}
