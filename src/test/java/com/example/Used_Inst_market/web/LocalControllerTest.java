package com.example.Used_Inst_market.web;

import com.example.Used_Inst_market.model.domain.local.upper.UpperLocal;
import com.example.Used_Inst_market.model.domain.local.upper.UpperLocalRepository;
import com.example.Used_Inst_market.web.dto.local.upper.UpperLocalInsertDTO;
import com.example.Used_Inst_market.web.dto.local.upper.UpperLocalUpdateDTO;
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
        Long testLocalNo = upperLocalRepository.save(UpperLocal.builder()
                .name("test")
                .build()).getUpperLocalNo();

        ResponseEntity<UpperLocalVO> responseEntity = testRestTemplate
                .getForEntity(URL + "?no=" + testLocalNo, UpperLocalVO.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getUpperLocalNo()).isEqualTo(testLocalNo);
    }

    @Test
    public void localSelectAll_검증() {
        List<UpperLocal> upperLocals = new ArrayList<>();
        upperLocals.add(UpperLocal.builder().name("test1").build());
        upperLocals.add(UpperLocal.builder().name("test2").build());

        upperLocalRepository.saveAll(upperLocals);

        ResponseEntity<List<UpperLocalVO>> responseEntity = testRestTemplate
                .exchange(URL + "/list", HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<UpperLocalVO>>() {});

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().get(0).getUpperLocalNo())
                .isEqualTo(upperLocalRepository.findAll().get(0).getUpperLocalNo());
        assertThat(responseEntity.getBody().get(1).getUpperLocalNo())
                .isEqualTo(upperLocalRepository.findAll().get(1).getUpperLocalNo());
    }

    @Test
    public void localInsert_검증() {
        UpperLocalInsertDTO upperLocalInsertDTO =
                UpperLocalInsertDTO.builder()
                        .name("test")
                        .build();

        ResponseEntity<Long> responseEntity = testRestTemplate
                .postForEntity(URL, upperLocalInsertDTO, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody())
                .isEqualTo(upperLocalRepository.findAll().get(0).getUpperLocalNo());
    }

    @Test
    public void localUpdate_검증() {
        UpperLocal testLocal = upperLocalRepository.save(
                UpperLocal.builder()
                        .name("test")
                        .build());

        UpperLocalUpdateDTO upperLocalUpdateDTO =
                UpperLocalUpdateDTO.builder()
                        .upperLocalNo(testLocal.getUpperLocalNo())
                        .name("updateTestName")
                        .build();

        ResponseEntity<Long> responseEntity = testRestTemplate
                .exchange(URL, HttpMethod.PUT,
                        new HttpEntity<>(upperLocalUpdateDTO),
                        Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(testLocal.getUpperLocalNo());
        assertThat(upperLocalRepository.findAll().get(0).getName())
                .isEqualTo("updateTestName");
    }

    @Test
    public void localDelete_검증() {
        Long testLocalNo = upperLocalRepository.save(UpperLocal.builder()
                .name("test")
                .build()).getUpperLocalNo();

        ResponseEntity<Void> responseEntity = testRestTemplate
                .exchange(URL + "?no=" + testLocalNo, HttpMethod.DELETE,
                        new HttpEntity<>(testLocalNo), Void.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNull();
        assertThat(upperLocalRepository.findById(testLocalNo)).isEmpty();
    }
}
