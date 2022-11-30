package com.example.Used_Inst_market.service;

import com.example.Used_Inst_market.domain.address.city.City;
import com.example.Used_Inst_market.domain.address.city.CityRepository;
import com.example.Used_Inst_market.domain.address.local.Local;
import com.example.Used_Inst_market.domain.address.local.LocalRepository;
import com.example.Used_Inst_market.service.address.CityService;
import com.example.Used_Inst_market.web.dto.address.city.CityDeleteRequestDTO;
import com.example.Used_Inst_market.web.dto.address.city.CityInsertRequestDTO;
import com.example.Used_Inst_market.web.dto.address.city.CitySelectRequestDTO;
import com.example.Used_Inst_market.web.dto.address.city.CityUpdateRequestDTO;
import com.example.Used_Inst_market.web.vo.address.CityVO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CityServiceTest {
    @Autowired private CityRepository cityRepository;
    @Autowired private CityService cityService;
    @Autowired private LocalRepository localRepository;

    @Before
    public void setup() {
        localRepository.save(Local.builder()
                .name("test")
                .build());
    }

    @After
    public void teardown() {
        cityRepository.deleteAll();
        localRepository.deleteAll();
    }

    @Test
    public void select_검증() {
        String testName = "test";
        Local testLocal = localRepository.findAll().get(0);

        City testCity = cityRepository.save(City.builder()
                .local(testLocal)
                .name(testName)
                .build());

        CityVO cityVO =
                cityService.select(new CitySelectRequestDTO(testCity.getCityNo()));

        assertThat(cityVO.getCityNo()).isEqualTo(testCity.getCityNo());
        assertThat(cityVO.getName()).isEqualTo(testName);
        assertThat(cityVO.getLocal().getLocalNo()).isEqualTo(testLocal.getLocalNo());
    }

    @Test
    public void selectAll_검증() {
        String testName1 = "test1";
        String testName2 = "test2";
        Local testLocal = localRepository.findAll().get(0);

        List<City> testCities = new ArrayList<City>();
        testCities.add(City.builder().name(testName1).local(testLocal).build());
        testCities.add(City.builder().name(testName2).local(testLocal).build());

        cityRepository.saveAll(testCities);
        List<CityVO> selectedCities = cityService.selectAll();

        assertThat(selectedCities.get(0).getCityNo()).isEqualTo(testCities.get(0).getCityNo());
        assertThat(selectedCities.get(0).getName()).isEqualTo(testCities.get(0).getName());
        assertThat(selectedCities.get(0).getLocal().getLocalNo())
                .isEqualTo(testCities.get(0).getLocal().getLocalNo());

        assertThat(selectedCities.get(1).getCityNo()).isEqualTo(testCities.get(1).getCityNo());
        assertThat(selectedCities.get(1).getName()).isEqualTo(testCities.get(1).getName());
        assertThat(selectedCities.get(1).getLocal().getLocalNo())
                .isEqualTo(testCities.get(1).getLocal().getLocalNo());
    }

    @Test
    public void insert_검증() {
        String testName = "test";
        Local testLocal = localRepository.findAll().get(0);

        Long testCityNo = cityService.insert(CityInsertRequestDTO.builder()
                .local(testLocal)
                .name(testName)
                .build());

        City city = cityRepository.findAll().get(0);

        assertThat(city.getCityNo()).isEqualTo(testCityNo);
        assertThat(city.getName()).isEqualTo(testName);
        assertThat(city.getLocal().getLocalNo()).isEqualTo(testLocal.getLocalNo());
    }

    @Test
    public void update_검증() {
        String testName = "test";
        Local local = localRepository.findAll().get(0);

        City testCity = cityRepository.save(City.builder()
                .name(testName)
                .local(local)
                .build());

        String updatedName = "update";
        Local updatedLocal = localRepository
                .save(Local.builder().name("update").build());

        cityService.update(CityUpdateRequestDTO.builder()
                .cityNo(testCity.getCityNo())
                .name(updatedName)
                .local(updatedLocal)
                .build());

        City updatedCity = cityRepository.findAll().get(0);

        assertThat(updatedCity.getCityNo()).isEqualTo(testCity.getCityNo());
        assertThat(updatedCity.getName()).isEqualTo(updatedName);
        assertThat(updatedCity.getLocal().getLocalNo())
                .isEqualTo(updatedLocal.getLocalNo());
    }

    @Test
    public void delete_검증() {
        City testCity = City.builder()
                .name("test")
                .local(localRepository.findAll().get(0))
                .build();

        cityRepository.save(testCity);
        Long testCityNo = cityRepository.findAll().get(0).getCityNo();

        cityService.delete(
                CityDeleteRequestDTO.builder()
                        .cityNo(testCityNo)
                        .build());

        assertThatIllegalArgumentException()
                .isThrownBy(() -> cityService.select(
                        CitySelectRequestDTO.builder()
                                .cityNo(testCityNo)
                                .build()));
    }
}
