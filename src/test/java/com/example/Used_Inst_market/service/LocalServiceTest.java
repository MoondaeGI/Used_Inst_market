package com.example.Used_Inst_market.service;

import com.example.Used_Inst_market.domain.address.local.Local;
import com.example.Used_Inst_market.domain.address.local.LocalRepository;
import com.example.Used_Inst_market.service.address.LocalService;
import com.example.Used_Inst_market.web.dto.address.local.*;
import com.example.Used_Inst_market.web.vo.address.LocalInfoVO;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LocalServiceTest {
    @Autowired
    private LocalRepository localRepository;

    @Autowired
    private LocalService localService;

    @After
    public void teardown() {
        localRepository.deleteAll();
    }

    @Test
    public void select_검증() {
        String testName = "test";
        Local testLocal = localRepository.save(Local.builder()
                .name(testName)
                .build());

        SelectLocalRequestDTO selectLocalRequestDTO = new SelectLocalRequestDTO(testLocal.getLocalNo());
        LocalInfoVO selectedLocal = localService.select(selectLocalRequestDTO);

        List<Local> locals = localRepository.findAll();

        assertThat(selectedLocal.getLocalNo()).isEqualTo(locals.get(0).getLocalNo());
        assertThat(selectedLocal.getName()).isEqualTo(locals.get(0).getName());
    }

    @Test
    public void selectAll_검증() {
        List<Local> testLocals = new ArrayList<Local>();
        testLocals.add(Local.builder().name("test1").build());
        testLocals.add(Local.builder().name("test2").build());

        localRepository.saveAll(testLocals);
        List<LocalInfoVO> localInfoVOList = localService.selectAll();

        assertThat(localInfoVOList.get(0).getName()).isEqualTo("test1");
        assertThat(localInfoVOList.get(1).getName()).isEqualTo("test2");
    }

    @Test
    public void insert_검증() {
        String testName = "test";
        InsertLocalRequestDTO insertLocalRequestDTO = new InsertLocalRequestDTO(testName);

        localService.insert(insertLocalRequestDTO);
        List<Local> locals = localRepository.findAll();

        assertThat(locals.get(0).getName()).isEqualTo(testName);
    }

    @Test
    public void update_검증() {
        String testName = "test";
        String updateTestName = "update test";

        localRepository.save(Local.builder()
                .name(testName)
                .build());
        Local testLocal = localRepository.findAll().get(0);

        UpdateLocalRequestDTO updateLocalRequestDTO
                = new UpdateLocalRequestDTO(testLocal.getLocalNo(), updateTestName);
        localService.update(updateLocalRequestDTO);

        assertThat(localRepository.findAll().get(0).getName()).isEqualTo(updateTestName);
    }

    @Test
    public void delete_검증() {
        Local testLocal = localRepository.save(Local.builder()
                .name("test")
                .build());

        Long testLocalNo = testLocal.getLocalNo();

        DeleteLocalRequestDTO deleteLocalRequestDTO = new DeleteLocalRequestDTO(testLocalNo);
        localService.delete(deleteLocalRequestDTO);
    }
}
