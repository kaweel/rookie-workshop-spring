package com.kaweel.rookieworkshopspring.student;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class StudentSpecification {

    public static Specification<Student> findByCriteria(StudentCriteria criteria) {
        return (Specification<Student>) (Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {

            List<Predicate> predicates = new ArrayList<>();

            String name = criteria.getName();
            if (!StringUtils.isEmpty(name)) {
                predicates.add(builder.equal(root.get("name"), name));
            }

            Grade grade = criteria.getGrade();
            if (null != grade) {
                predicates.add(builder.equal(root.get("grade"), grade));
            }

            return builder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    public static Specification<Student> nameIs(String name) {
        return (Specification<Student>) (Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> builder.equal(root.get("name"), name);
    }

    public static Specification<Student> passwordIs(String password) {
        return (Specification<Student>) (Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> builder.equal(root.get("password"), password);
    }

    public static Specification<Student> gradeIs(Grade grade) {
        return (Specification<Student>) (Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> builder.equal(root.get("grade"), grade);
    }

}
