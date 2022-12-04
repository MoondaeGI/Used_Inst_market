package com.example.Used_Inst_market.service;

import com.example.Used_Inst_market.domain.city.City;
import com.example.Used_Inst_market.domain.city.CityRepository;
import com.example.Used_Inst_market.domain.local.Local;
import com.example.Used_Inst_market.domain.local.LocalRepository;
import com.example.Used_Inst_market.service.local.LocalService;
import com.example.Used_Inst_market.web.dto.address.city.CityInsertRequestDTO;
import com.example.Used_Inst_market.web.dto.address.city.CitySelectRequestDTO;
import com.example.Used_Inst_market.web.dto.address.local.*;
import com.example.Used_Inst_market.web.vo.address.CityVO;
import com.example.Used_Inst_market.web.vo.address.LocalVO;
import org.junit.After;
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
public class LocalServiceTest {
    @Autowired private LocalRepository localRepository;
    @Autowired private LocalService localService;
    @Autowired private CityRepository cityRepository;

    @After
    public void teardown() {
        localRepository.deleteAll();
    }

    @Test
    public void localSelect_검증() {
        String testName = "test";
        Local testLocal = localRepository.save(Local.builder()
                .name(testName)
                .build());

        LocalSelectRequestDTO localSelectRequestDTO = new LocalSelectRequestDTO(testLocal.getLocalNo());
        LocalVO selectedLocal = localService.localSelect(localSelectRequestDTO);

        List<Local> locals = localRepository.findAll();

        assertThat(selectedLocal.getLocalNo()).isEqualTo(locals.get(0).getLocalNo());
        assertThat(selectedLocal.getName()).isEqualTo(locals.get(0).getName());
    }

    @Test
    public void localSelectAll_검증() {
        List<Local> testLocals = new ArrayList<Local>();
        testLocals.add(Local.builder().name("test1").build());
        testLocals.add(Local.builder().name("test2").build());

        localRepository.saveAll(testLocals);
        List<LocalVO> localVOList = localService.localSelectAll();

        assertThat(localVOList.get(0).getName()).isEqualTo("test1");
        assertThat(localVOList.get(1).getName()).isEqualTo("test2");
    }

    @Test
    public void localInsert_검증() {
        String testName = "test";
        LocalInsertRequestDTO localInsertRequestDTO = new LocalInsertRequestDTO(testName);

        localService.localInsert(localInsertRequestDTO);
        List<Local> locals = localRepository.findAll();

        assertThat(locals.get(0).getName()).isEqualTo(testName);
    }

    @Test
    public void localUpdate_검증() {
        String testName = "test";
        String updateTestName = "update test";

        localRepository.save(Local.builder()
                .name(testName)
                .build());
        Local testLocal = localRepository.findAll().get(0);

        LocalUpdateRequestDTO localUpdateRequestDTO
                = new LocalUpdateRequestDTO(testLocal.getLocalNo(), updateTestName);
        localService.localUpdate(localUpdateRequestDTO);

        assertThat(localRepository.findAll().get(0).getName()).isEqualTo(updateTestName);
    }

    @Test
    public void localDelete_검증() {
        Local testLocal = localRepository.save(Local.builder()
                .name("test")
                .build());

        Long testLocalNo = testLocal.getLocalNo();

        localService.localDelete(LocalDeleteRequestDTO.builder()
                .localNo(testLocalNo)
                .build());

        assertThatIllegalArgumentException()
                .isThrownBy(() -> localService.localSelect(
                        LocalSelectRequestDTO.builder()
                                .localNo(testLocalNo)
                                .build()));
    }

    @Test
    public void citySelect_검증() {
        Local testLocal = localRepository.save(Local.builder()
                .name("test")
                .build());

        City testCity = cityRepository.save(City.builder()
                .local(testLocal)
                .name("test")
                .build());

        CitySelectRequestDTO citySelectRequestDTO =
                CitySelectRequestDTO.builder()
                        .cityNo(testCity.getCityNo())
                        .build();

        CityVO testCityVO = localService.citySelect(citySelectRequestDTO);

        assertThat(testCityVO.getCityNo())
                .isEqualTo(cityRepository.findAll().get(0).getCityNo());
    }

    @Test
    public void cityInsert_검증() {
        Local testLocal = localRepository.save(Local.builder()
                .name("test")
                .build());

        CityInsertRequestDTO cityInsertRequestDTO =
                CityInsertRequestDTO.builder()
                        .local(testLocal)
                        .name("test")
                        .build();

        Long testCityNo = localService.cityInsert(cityInsertRequestDTO);

        assertThat(testCityNo).isEqualTo(cityRepository.findAll().get(0).getCityNo());
    }
}
