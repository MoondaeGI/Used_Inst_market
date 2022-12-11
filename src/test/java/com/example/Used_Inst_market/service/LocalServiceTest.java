package com.example.Used_Inst_market.service;

import com.example.Used_Inst_market.model.domain.local.lower.LowerLocal;
import com.example.Used_Inst_market.model.domain.local.lower.LowerLocalRepository;
import com.example.Used_Inst_market.model.domain.local.upper.UpperLocal;
import com.example.Used_Inst_market.model.domain.local.upper.UpperLocalRepository;
import com.example.Used_Inst_market.service.local.LocalService;
import com.example.Used_Inst_market.web.dto.local.lower.LowerLocalInsertRequestDTO;
import com.example.Used_Inst_market.web.dto.local.lower.LowerLocalSelectRequestDTO;
import com.example.Used_Inst_market.web.dto.local.upper.UpperLocalDeleteRequestDTO;
import com.example.Used_Inst_market.web.dto.local.upper.UpperLocalInsertRequestDTO;
import com.example.Used_Inst_market.web.dto.local.upper.UpperLocalSelectRequestDTO;
import com.example.Used_Inst_market.web.dto.local.upper.UpperLocalUpdateRequestDTO;
import com.example.Used_Inst_market.model.vo.local.LowerLocalVO;
import com.example.Used_Inst_market.model.vo.local.UpperLocalVO;
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
    @Autowired private UpperLocalRepository upperLocalRepository;
    @Autowired private LocalService localService;
    @Autowired private LowerLocalRepository lowerLocalRepository;

    @After
    public void teardown() {
        upperLocalRepository.deleteAll();
    }

    @Test
    public void localSelect_검증() {
        String testName = "test";
        UpperLocal testLocal = upperLocalRepository.save(UpperLocal.builder()
                .name(testName)
                .build());

        UpperLocalSelectRequestDTO upperLocalSelectRequestDTO =
                new UpperLocalSelectRequestDTO(testLocal.getUpperLocalNo());
        UpperLocalVO selectedLocal =
                localService.upperLocalSelect(upperLocalSelectRequestDTO);

        List<UpperLocal> locals = upperLocalRepository.findAll();

        assertThat(selectedLocal.getUpperLocalNo())
                .isEqualTo(locals.get(0).getUpperLocalNo());
        assertThat(selectedLocal.getName()).isEqualTo(locals.get(0).getName());
    }

    @Test
    public void localSelectAll_검증() {
        List<UpperLocal> testUpperLocals = new ArrayList<UpperLocal>();
        testUpperLocals.add(UpperLocal.builder().name("test1").build());
        testUpperLocals.add(UpperLocal.builder().name("test2").build());

        upperLocalRepository.saveAll(testUpperLocals);
        List<UpperLocalVO> upperLocalVOList = localService.upperLocalSelectAll();

        assertThat(upperLocalVOList.get(0).getName()).isEqualTo("test1");
        assertThat(upperLocalVOList.get(1).getName()).isEqualTo("test2");
    }

    @Test
    public void localInsert_검증() {
        String testName = "test";
        UpperLocalInsertRequestDTO upperLocalInsertRequestDTO =
                new UpperLocalInsertRequestDTO(testName);

        localService.upperLocalInsert(upperLocalInsertRequestDTO);
        List<UpperLocal> upperLocals = upperLocalRepository.findAll();

        assertThat(upperLocals.get(0).getName()).isEqualTo(testName);
    }

    @Test
    public void upperLocalUpdate_검증() {
        String testName = "test";
        String updateTestName = "update test";

        upperLocalRepository.save(UpperLocal.builder()
                .name(testName)
                .build());
        UpperLocal testUpperLocal = upperLocalRepository.findAll().get(0);

        UpperLocalUpdateRequestDTO upperLocalUpdateRequestDTO =
                UpperLocalUpdateRequestDTO.builder()
                        .upperLocalNo(testUpperLocal.getUpperLocalNo())
                        .name(updateTestName)
                        .build();

        localService.upperLocalUpdate(upperLocalUpdateRequestDTO);

        assertThat(upperLocalRepository.findAll().get(0).getName())
                .isEqualTo(updateTestName);
    }

    @Test
    public void localDelete_검증() {
        UpperLocal testLocal = upperLocalRepository.save(UpperLocal.builder()
                .name("test")
                .build());

        Long testUpperLocalNo = testLocal.getUpperLocalNo();

        localService.upperLocalDelete(UpperLocalDeleteRequestDTO.builder()
                .upperLocalNo(testUpperLocalNo)
                .build());

        assertThatIllegalArgumentException()
                .isThrownBy(() -> localService.upperLocalSelect(
                        UpperLocalSelectRequestDTO.builder()
                                .upperLocalNo(testUpperLocalNo)
                                .build()));
    }

    @Test
    public void citySelect_검증() {
        UpperLocal testUpperLocal = upperLocalRepository.save(UpperLocal.builder()
                .name("test")
                .build());

        LowerLocal testLowerLocal = lowerLocalRepository.save(LowerLocal.builder()
                .upperLocal(testUpperLocal)
                .name("test")
                .build());

        LowerLocalSelectRequestDTO lowerLocalSelectRequestDTO =
                LowerLocalSelectRequestDTO.builder()
                        .lowerLocalNo(testLowerLocal.getLowerLocalNo())
                        .build();

        LowerLocalVO testCityVO = localService
                .lowerLocalSelect(lowerLocalSelectRequestDTO);

        assertThat(testCityVO.getLowerLocalNo())
                .isEqualTo(lowerLocalRepository
                        .findAll().get(0).getLowerLocalNo());
    }

    @Test
    public void cityInsert_검증() {
        UpperLocal testUpperLocal = upperLocalRepository.save(UpperLocal.builder()
                .name("test")
                .build());

        LowerLocalInsertRequestDTO lowerLocalInsertRequestDTO =
                LowerLocalInsertRequestDTO.builder()
                        .upperLocal(testUpperLocal)
                        .name("test")
                        .build();

        Long testCityNo = localService.lowerLocalInsert(lowerLocalInsertRequestDTO);

        assertThat(testCityNo)
                .isEqualTo(lowerLocalRepository.findAll().get(0).getLowerLocalNo());
    }
}
