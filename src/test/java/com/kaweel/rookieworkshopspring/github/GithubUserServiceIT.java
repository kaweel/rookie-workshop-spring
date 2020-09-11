package com.kaweel.rookieworkshopspring.github;

import com.kaweel.rookieworkshopspring.RookieWorkshopSpringApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.URISyntaxException;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = RookieWorkshopSpringApplication.class)
@Tag("integration")
public class GithubUserServiceIT {

    @Autowired
    GithubUserService githubUserService;

    @Test
    @DisplayName("get user by username is 'kaweel', found http status should be 'OK' and name is 'Kawee Lertrungmongkol'")
    public void found() throws URISyntaxException {
        ResponseEntity<GithubUserService.User> responseEntity = githubUserService.getByUserName("kaweel");

        HttpStatus httpStatus = responseEntity.getStatusCode();
        Assertions.assertEquals(HttpStatus.OK, httpStatus);

        GithubUserService.User user = responseEntity.getBody();
        Assertions.assertEquals("kaweel", user.getLogin());
        Assertions.assertEquals("Kawee Lertrungmongkol", user.getName());
        Assertions.assertEquals("https://medium.com/@kaweel", user.getBlog());
    }

    @Test
    @DisplayName("get user by username is 'sommeaw', not found http status should be 'NOT_FOUND'")
    public void not_found() throws URISyntaxException {
        ResponseEntity<GithubUserService.User> responseEntity = githubUserService.getByUserName("sommeaw");
        HttpStatus httpStatus = responseEntity.getStatusCode();
        Assertions.assertEquals(HttpStatus.NOT_FOUND, httpStatus);
    }

}
