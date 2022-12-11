package com.example.Used_Inst_market.web.controller;

import com.example.Used_Inst_market.model.domain.local.upper.Local;
import com.example.Used_Inst_market.model.domain.local.upper.UpperLocalRepository;
import com.example.Used_Inst_market.web.dto.local.upper.UpperLocalInsertRequestDTO;
import com.example.Used_Inst_market.web.dto.local.upper.UpperLocalUpdateRequestDTO;
import com.example.Used_Inst_market.model.vo.local.UpperLocalVO;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LocalControllerTest {
    private static final String URL = "/local/info";
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private UpperLocalRepository upperLocalRepository;

    @After
    public void teardown() {
        upperLocalRepository.deleteAll();
    }

    @Test
    public void localSelect_검증() {
        Long testLocalNo = upperLocalRepository.save(Local.builder()
                .name("test")
                .build()).getLocalNo();

        ResponseEntity<UpperLocalVO> responseEntity = testRestTemplate
                .getForEntity(URL + "?no=" + testLocalNo, UpperLocalVO.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getLocalNo()).isEqualTo(testLocalNo);
    }

    @Test
    public void localSelectAll_검증() {
        List<Local> locals = new ArrayList<>();
        locals.add(Local.builder().name("test1").build());
        locals.add(Local.builder().name("test2").build());

        upperLocalRepository.saveAll(locals);

        ResponseEntity<List<UpperLocalVO>> responseEntity = testRestTemplate
                .exchange(URL + "/list", HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<UpperLocalVO>>() {});

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().get(0).getLocalNo())
                .isEqualTo(upperLocalRepository.findAll().get(0).getLocalNo());
        assertThat(responseEntity.getBody().get(1).getLocalNo())
                .isEqualTo(upperLocalRepository.findAll().get(1).getLocalNo());
    }

    @Test
    public void localInsert_검증() {
        UpperLocalInsertRequestDTO upperLocalInsertRequestDTO =
                UpperLocalInsertRequestDTO.builder()
                        .name("test")
                        .build();

        ResponseEntity<Long> responseEntity = testRestTemplate
                .postForEntity(URL, upperLocalInsertRequestDTO, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody())
                .isEqualTo(upperLocalRepository.findAll().get(0).getLocalNo());
    }

    @Test
    public void localUpdate_검증() {
        Local testLocal = upperLocalRepository.save(
                Local.builder()
                        .name("test")
                        .build());

        UpperLocalUpdateRequestDTO upperLocalUpdateRequestDTO =
                UpperLocalUpdateRequestDTO.builder()
                        .localNo(testLocal.getLocalNo())
                        .name("updateTestName")
                        .build();

        ResponseEntity<Long> responseEntity = testRestTemplate
                .exchange(URL, HttpMethod.PUT,
                        new HttpEntity<>(upperLocalUpdateRequestDTO),
                        Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(testLocal.getLocalNo());
        assertThat(upperLocalRepository.findAll().get(0).getName()).isEqualTo("updateTestName");
    }

    @Test
    public void localDelete_검증() {
        Long testLocalNo = upperLocalRepository.save(Local.builder()
                .name("test")
                .build()).getLocalNo();

        ResponseEntity<Void> responseEntity = testRestTemplate
                .exchange(URL + "?no=" + testLocalNo, HttpMethod.DELETE,
                        new HttpEntity<>(testLocalNo), Void.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNull();
        assertThat(upperLocalRepository.findById(testLocalNo)).isEmpty();
    }
}
