package com.kaweel.rookieworkshopspring.student;

import com.kaweel.rookieworkshopspring.entity.ActionEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "subject")
@EqualsAndHashCode(callSuper = false)
public class Subject extends ActionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "subject", fetch = FetchType.LAZY)
    private Set<StudentSubject> studentSubjects;

}
