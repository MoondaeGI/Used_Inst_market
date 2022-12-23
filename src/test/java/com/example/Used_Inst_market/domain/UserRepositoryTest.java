package com.example.Used_Inst_market.domain;

import com.example.Used_Inst_market.model.domain.user.Role;
import com.example.Used_Inst_market.model.domain.user.User;
import com.example.Used_Inst_market.model.domain.user.UserRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired private UserRepository userRepository;

    @After
    public void teardown() {
        userRepository.deleteAll();
    }

    @Test
    public void TB_USER_등록_검증() {
        String testName = "test";

        User testUser = userRepository.save(
                User.builder()
                        .name(testName)
                        .email("test1234@naver.com")
                        .picture("test_picture")
                        .role(Role.ADMIN)
                        .build());

        assertThat(userRepository.findAll().get(0).getUserNo()).isEqualTo(testUser.getUserNo());
        assertThat(userRepository.findAll().get(0).getName()).isEqualTo(testName);
    }

    @Test(expected = ConstraintViolationException.class)
    public void email_검증() {
        String testEmail = "not email";

        userRepository.save(
                User.builder()
                        .name("test")
                        .email(testEmail)
                        .picture("test_picture")
                        .role(Role.ADMIN)
                        .build());
    }
}
