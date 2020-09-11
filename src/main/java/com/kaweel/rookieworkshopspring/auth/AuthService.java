package com.kaweel.rookieworkshopspring.auth;

import com.kaweel.rookieworkshopspring.auth.model.SignInRequest;
import com.kaweel.rookieworkshopspring.auth.model.SignInResponse;
import com.kaweel.rookieworkshopspring.config.handle.BusinessException;
import com.kaweel.rookieworkshopspring.student.Student;
import com.kaweel.rookieworkshopspring.student.StudentRepository;
import com.kaweel.rookieworkshopspring.student.StudentSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class AuthService {

    private StudentRepository studentRepository;
    private Map<String, String> tokenMap = new HashMap<>();

    @Autowired
    public AuthService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Boolean tokenIsValid(String token) {
        if (null == token) {
            return false;
        }
        return tokenMap.containsValue(token);
    }

    public SignInResponse signIn(SignInRequest request) throws Throwable {
        Student student = (Student) studentRepository.findOne(
                StudentSpecification
                        .nameIs(request.getName())
                        .and(StudentSpecification.passwordIs(request.getPwd()))
        ).orElseThrow(() -> new BusinessException(HttpStatus.UNAUTHORIZED));
        String token = UUID.randomUUID().toString();
        tokenMap.put(student.getName(), token);
        return new SignInResponse(token);
    }
}
