package com.example.Used_Inst_market.service;

import com.example.Used_Inst_market.model.domain.local.lower.LowerLocal;
import com.example.Used_Inst_market.model.domain.local.lower.LowerLocalRepository;
import com.example.Used_Inst_market.model.domain.local.upper.UpperLocal;
import com.example.Used_Inst_market.model.domain.local.upper.UpperLocalRepository;
import com.example.Used_Inst_market.service.local.LocalService;
import com.example.Used_Inst_market.web.dto.local.lower.LowerLocalInsertDTO;
import com.example.Used_Inst_market.web.dto.local.lower.LowerLocalSelectDTO;
import com.example.Used_Inst_market.web.dto.local.upper.UpperLocalDeleteDTO;
import com.example.Used_Inst_market.web.dto.local.upper.UpperLocalInsertDTO;
import com.example.Used_Inst_market.web.dto.local.upper.UpperLocalSelectDTO;
import com.example.Used_Inst_market.web.dto.local.upper.UpperLocalUpdateDTO;
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

        UpperLocalSelectDTO upperLocalSelectDTO =
                new UpperLocalSelectDTO(testLocal.getUpperLocalNo());
        UpperLocalVO selectedLocal =
                localService.upperLocalSelect(upperLocalSelectDTO);

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
        UpperLocalInsertDTO upperLocalInsertDTO =
                new UpperLocalInsertDTO(testName);

        localService.upperLocalInsert(upperLocalInsertDTO);
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

        UpperLocalUpdateDTO upperLocalUpdateDTO =
                UpperLocalUpdateDTO.builder()
                        .upperLocalNo(testUpperLocal.getUpperLocalNo())
                        .name(updateTestName)
                        .build();

        localService.upperLocalUpdate(upperLocalUpdateDTO);

        assertThat(upperLocalRepository.findAll().get(0).getName())
                .isEqualTo(updateTestName);
    }

    @Test
    public void localDelete_검증() {
        UpperLocal testLocal = upperLocalRepository.save(UpperLocal.builder()
                .name("test")
                .build());

        Long testUpperLocalNo = testLocal.getUpperLocalNo();

        localService.upperLocalDelete(UpperLocalDeleteDTO.builder()
                .upperLocalNo(testUpperLocalNo)
                .build());

        assertThatIllegalArgumentException()
                .isThrownBy(() -> localService.upperLocalSelect(
                        UpperLocalSelectDTO.builder()
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

        LowerLocalSelectDTO lowerLocalSelectDTO =
                LowerLocalSelectDTO.builder()
                        .lowerLocalNo(testLowerLocal.getLowerLocalNo())
                        .build();

        LowerLocalVO testCityVO = localService
                .lowerLocalSelect(lowerLocalSelectDTO);

        assertThat(testCityVO.getLowerLocalNo())
                .isEqualTo(lowerLocalRepository
                        .findAll().get(0).getLowerLocalNo());
    }

    @Test
    public void cityInsert_검증() {
        UpperLocal testUpperLocal = upperLocalRepository.save(UpperLocal.builder()
                .name("test")
                .build());

        LowerLocalInsertDTO lowerLocalInsertDTO =
                LowerLocalInsertDTO.builder()
                        .upperLocalNo(testUpperLocal.getUpperLocalNo())
                        .name("test")
                        .build();

        Long testCityNo = localService.lowerLocalInsert(lowerLocalInsertDTO);

        assertThat(testCityNo)
                .isEqualTo(lowerLocalRepository.findAll().get(0).getLowerLocalNo());
    }
}
