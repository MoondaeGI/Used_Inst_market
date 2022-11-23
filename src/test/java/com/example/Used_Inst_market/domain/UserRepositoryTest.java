package com.example.Used_Inst_market.domain;

import com.example.Used_Inst_market.domain.user.Role;
import com.example.Used_Inst_market.domain.user.User;
import com.example.Used_Inst_market.domain.user.UserRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @After
    public void teardown() {
        userRepository.deleteAll();
    }

    @Test
    public void TB_USER_추가() throws Exception {
        String testName = "test";
        String testEmail = "test1234@test.com";
        String testPhoneNumber = "010-0000-0000";

        User user = User.builder()
                .name(testName)
                .email(testEmail)
                .phoneNumber(testPhoneNumber)
                .build();

        userRepository.save(user);
        List<User> users = userRepository.findAll();

        assertThat(users.get(0).getName()).isEqualTo(testName);
        assertThat(users.get(0).getEmail()).isEqualTo(testEmail);
        assertThat(users.get(0).getPhoneNumber()).isEqualTo(testPhoneNumber);
        assertThat(users.get(0).getRole()).isEqualTo(Role.GUEST);
    }
}
