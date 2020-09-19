package com.kaweel.rookieworkshopspring.auth;

import com.kaweel.rookieworkshopspring.auth.model.SignInRequest;
import com.kaweel.rookieworkshopspring.auth.model.SignInResponse;
import com.kaweel.rookieworkshopspring.config.handle.CustomException;
import com.kaweel.rookieworkshopspring.student.Student;
import com.kaweel.rookieworkshopspring.student.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AuthService {

    private StudentRepository studentRepository;

    @Autowired
    public AuthService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    private Map<String, String> tokenMap = new HashMap<>();

    public Boolean validateToken(String token) {
        if (null == token) {
            return false;
        }
        return tokenMap.containsValue(token);
    }

    public SignInResponse signIn(SignInRequest signInRequest) {
        Student student = studentRepository.findByNameAndPassword(signInRequest.getName(), signInRequest.getPassword())
                .orElseThrow(() -> new CustomException(HttpStatus.FORBIDDEN, "invalid credentials"));
        String token = UUID.randomUUID().toString();
        tokenMap.put(student.getName(), token);
        return new SignInResponse(token);
    }

    public void signOut(String token) {
        String key = tokenMap
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(token))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
        tokenMap.remove(key);
    }
}
