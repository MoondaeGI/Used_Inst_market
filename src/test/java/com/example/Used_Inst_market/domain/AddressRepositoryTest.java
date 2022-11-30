package com.example.Used_Inst_market.domain;

import com.example.Used_Inst_market.domain.address.addressdetail.AddressRepository;
import com.example.Used_Inst_market.domain.address.city.CityRepository;
import com.example.Used_Inst_market.domain.address.local.Local;
import com.example.Used_Inst_market.domain.address.local.LocalRepository;
import com.example.Used_Inst_market.domain.user.UserRepository;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressRepositoryTest {
    @Autowired private AddressRepository addressRepository;

    @Autowired private UserRepository userRepository;
    @Autowired private LocalRepository localRepository;
    @Autowired private CityRepository cityRepository;

    @Before
    public void setup() {
        Local testLocal = localRepository.save(
                Local.builder().name("test").build());


    }
}
