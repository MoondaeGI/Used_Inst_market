package com.example.Used_Inst_market.service;

import com.example.Used_Inst_market.domain.address.city.CityRepository;
import com.example.Used_Inst_market.domain.address.local.Local;
import com.example.Used_Inst_market.domain.address.local.LocalRepository;
import com.example.Used_Inst_market.service.address.CityService;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CityServiceTest {
    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CityService cityService;

    @Autowired
    private LocalRepository localRepository;

    @Before
    public void setup() {
        localRepository.save(Local.builder()
                .name("test")
                .build());
    }

    @After
    public void teardown() {
        localRepository.deleteAll();
        cityRepository.deleteAll();
    }
}
