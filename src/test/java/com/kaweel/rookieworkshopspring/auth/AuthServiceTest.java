package com.kaweel.rookieworkshopspring.auth;

import com.kaweel.rookieworkshopspring.auth.model.SignInRequest;
import com.kaweel.rookieworkshopspring.auth.model.SignInResponse;
import com.kaweel.rookieworkshopspring.config.handle.CustomException;
import com.kaweel.rookieworkshopspring.student.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Sql(value = {"/sql_script/student_data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/sql_script/subject_data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/sql_script/student_subject_data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/sql_script/student_cleanup.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Tag("basic")
public class AuthServiceTest {

    @Autowired
    StudentRepository studentRepository;

    @Test
    void dummy() {
        AuthService service = new AuthService(new DummyStudentRepository());
        Assertions.assertNotNull(service);
    }

    @Test
    void stub() {
        AuthService service = new AuthService(new StubStudentRepository());
        Assertions.assertThrows(
                CustomException.class,
                () -> service.signIn(new SignInRequest()), HttpStatus.UNAUTHORIZED.getReasonPhrase()
        );
    }

    @Test
    void spy() {
        SpyStudentRepository spy = new SpyStudentRepository();
        AuthService service = new AuthService(spy);
        Assertions.assertThrows(
                CustomException.class,
                () -> service.signIn(new SignInRequest()), HttpStatus.UNAUTHORIZED.getReasonPhrase()
        );
        Assertions.assertEquals(true, spy.isFindByUserNameWasCalled());
    }

    @Test
    void mock() throws Throwable {
        MockStudentRepository mock = new MockStudentRepository();
        AuthService service = new AuthService(mock);
        SignInResponse signInResponse = service.signIn(new SignInRequest());
        Assertions.assertNotNull(signInResponse.getToken());
        Assertions.assertEquals(true, mock.isFindByUserNameWasCalled());
    }

    @Test
    void fake_fail() {
        AuthService service = new AuthService(studentRepository);
        Assertions.assertThrows(
                CustomException.class,
                () -> service.signIn(new SignInRequest()), HttpStatus.UNAUTHORIZED.getReasonPhrase()
        );
    }

    @Test
    void fake_success() throws Throwable {
        AuthService service = new AuthService(studentRepository);
        SignInRequest signInRequest = new SignInRequest();
        signInRequest.setName("sephera");
        signInRequest.setPassword("sp");
        SignInResponse signInResponse = service.signIn(signInRequest);
        Assertions.assertNotNull(signInResponse.getToken());
    }

}
