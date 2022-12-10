package com.example.Used_Inst_market.domain;

import com.example.Used_Inst_market.model.domain.local.Local;
import com.example.Used_Inst_market.model.domain.local.LocalRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseTimeStampTest {
    @Autowired
    private LocalRepository localRepository;

    @After
    public void teardown() {
        localRepository.deleteAll();
    }

    @Test
    public void BaseTimeStamp_등록_검증() {
        LocalDateTime testDate =
                LocalDateTime.of(2022, 11, 25, 0, 0, 0);

        localRepository.save(Local.builder()
                .name("test")
                .build());
        List<Local> locals = localRepository.findAll();

        assertThat(locals.get(0).getRegDt()).isAfter(testDate);
        assertThat(locals.get(0).getModDt()).isAfter(testDate);
    }

    @Test
    public void modDt_검증() {
        LocalDateTime testDate =
                LocalDateTime.of(2022, 11, 30, 0, 0, 0);

        localRepository.save(Local.builder()
                .name("test")
                .build());
    }
}
