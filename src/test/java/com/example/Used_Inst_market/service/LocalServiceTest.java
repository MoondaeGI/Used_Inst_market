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
        List<Local> testLocals = new ArrayList<Local>();
        testLocals.add(Local.builder().name("test1").build());
        testLocals.add(Local.builder().name("test2").build());

        upperLocalRepository.saveAll(testLocals);
        List<UpperLocalVO> upperLocalVOList = localService.localSelectAll();

        assertThat(upperLocalVOList.get(0).getName()).isEqualTo("test1");
        assertThat(upperLocalVOList.get(1).getName()).isEqualTo("test2");
    }

    @Test
    public void localInsert_검증() {
        String testName = "test";
        UpperLocalInsertRequestDTO upperLocalInsertRequestDTO = new UpperLocalInsertRequestDTO(testName);

        localService.localInsert(upperLocalInsertRequestDTO);
        List<Local> locals = upperLocalRepository.findAll();

        assertThat(locals.get(0).getName()).isEqualTo(testName);
    }

    @Test
    public void localUpdate_검증() {
        String testName = "test";
        String updateTestName = "update test";

        upperLocalRepository.save(Local.builder()
                .name(testName)
                .build());
        Local testLocal = upperLocalRepository.findAll().get(0);

        UpperLocalUpdateRequestDTO upperLocalUpdateRequestDTO
                = new UpperLocalUpdateRequestDTO(testLocal.getLocalNo(), updateTestName);
        localService.localUpdate(upperLocalUpdateRequestDTO);

        assertThat(upperLocalRepository.findAll().get(0).getName()).isEqualTo(updateTestName);
    }

    @Test
    public void localDelete_검증() {
        Local testLocal = upperLocalRepository.save(Local.builder()
                .name("test")
                .build());

        Long testLocalNo = testLocal.getLocalNo();

        localService.localDelete(UpperLocalDeleteRequestDTO.builder()
                .localNo(testLocalNo)
                .build());

        assertThatIllegalArgumentException()
                .isThrownBy(() -> localService.localSelect(
                        UpperLocalSelectRequestDTO.builder()
                                .localNo(testLocalNo)
                                .build()));
    }

    @Test
    public void citySelect_검증() {
        Local testLocal = upperLocalRepository.save(Local.builder()
                .name("test")
                .build());

        LowerLocal testLowerLocal = lowerLocalRepository.save(LowerLocal.builder()
                .local(testLocal)
                .name("test")
                .build());

        LowerLocalSelectRequestDTO lowerLocalSelectRequestDTO =
                LowerLocalSelectRequestDTO.builder()
                        .cityNo(testLowerLocal.getCityNo())
                        .build();

        LowerLocalVO testCityVO = localService.citySelect(lowerLocalSelectRequestDTO);

        assertThat(testCityVO.getCityNo())
                .isEqualTo(lowerLocalRepository.findAll().get(0).getCityNo());
    }

    @Test
    public void cityInsert_검증() {
        Local testLocal = upperLocalRepository.save(Local.builder()
                .name("test")
                .build());

        LowerLocalInsertRequestDTO lowerLocalInsertRequestDTO =
                LowerLocalInsertRequestDTO.builder()
                        .local(testLocal)
                        .name("test")
                        .build();

        Long testCityNo = localService.cityInsert(lowerLocalInsertRequestDTO);

        assertThat(testCityNo).isEqualTo(lowerLocalRepository.findAll().get(0).getCityNo());
    }
}
