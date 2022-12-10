package com.example.Used_Inst_market.domain;

import com.example.Used_Inst_market.model.domain.city.City;
import com.example.Used_Inst_market.model.domain.city.CityRepository;
import com.example.Used_Inst_market.model.domain.local.Local;
import com.example.Used_Inst_market.model.domain.local.LocalRepository;
import com.example.Used_Inst_market.model.domain.user.User;
import com.example.Used_Inst_market.model.domain.user.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired private UserRepository userRepository;
    @Autowired private LocalRepository localRepository;
    @Autowired private CityRepository cityRepository;

    @Before
    public void setup() {
        Local testLocal = localRepository.save(
                Local.builder().name("test").build());

        City testCity = cityRepository.save(
                City.builder().local(testLocal).name("test").build());
    }

    @After
    public void teardown() {
        userRepository.deleteAll();
        localRepository.deleteAll();
    }

    @Test
    public void TB_USER_등록_검증() {
        String testName = "test";

        User testUser = userRepository.save(
                User.builder()
                        .name(testName)
                        .email("test1234@naver.com")
                        .phoneNumber("010-0000-0000")
                        .build());

        assertThat(userRepository.findAll().get(0).getUserNo()).isEqualTo(testUser.getUserNo());
        assertThat(userRepository.findAll().get(0).getName()).isEqualTo(testName);
    }
}
