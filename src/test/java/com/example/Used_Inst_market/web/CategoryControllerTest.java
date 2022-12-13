package com.example.Used_Inst_market.web;

import com.example.Used_Inst_market.model.domain.category.lower.LowerCategory;
import com.example.Used_Inst_market.model.domain.category.lower.LowerCategoryRepository;
import com.example.Used_Inst_market.model.domain.category.upper.UpperCategory;
import com.example.Used_Inst_market.model.domain.category.upper.UpperCategoryRepository;
import com.example.Used_Inst_market.web.dto.category.lower.LowerCategoryInsertRequestDTO;
import com.example.Used_Inst_market.web.dto.category.lower.LowerCategoryUpdateRequestDTO;
import com.example.Used_Inst_market.web.dto.category.upper.UpperCategoryInsertRequestDTO;
import com.example.Used_Inst_market.web.dto.category.upper.UpperCategoryUpdateRequestDTO;
import com.example.Used_Inst_market.model.vo.category.LowerCategoryVO;
import com.example.Used_Inst_market.model.vo.category.UpperCategoryVO;
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
public class CategoryControllerTest {
    private static final String UPPER_CATEGORY_URL = "/category/upper/info";
    private static final String LOWER_CATEGORY_URL = "/category/lower/info";

    @Autowired
    private UpperCategoryRepository upperCategoryRepository;

    @Autowired
    private LowerCategoryRepository lowerCategoryRepository;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @After
    public void teardown() {
        lowerCategoryRepository.deleteAll();
        upperCategoryRepository.deleteAll();
    }

    @Test
    public void upperCategorySelect_검증() {
        Long testUpperCategoryNo = upperCategoryRepository.save(
                UpperCategory.builder()
                        .name("test")
                        .build()).getUpperCategoryNo();

        ResponseEntity<UpperCategoryVO> responseEntity = testRestTemplate
                .getForEntity(UPPER_CATEGORY_URL + "?no=" + testUpperCategoryNo,
                        UpperCategoryVO.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getUpperCategoryNo())
                .isEqualTo(testUpperCategoryNo);
    }

    @Test
    public void upperCategorySelectAll_검증() {
        List<UpperCategory> testUpperCategoryList = new ArrayList<>();
        testUpperCategoryList.add(UpperCategory.builder().name("test1").build());
        testUpperCategoryList.add(UpperCategory.builder().name("test2").build());

        upperCategoryRepository.saveAll(testUpperCategoryList);

        ResponseEntity<List<UpperCategoryVO>> responseEntity = testRestTemplate
                .exchange(UPPER_CATEGORY_URL + "/list", HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<UpperCategoryVO>>() {});

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().get(0).getUpperCategoryNo())
                .isEqualTo(testUpperCategoryList.get(0).getUpperCategoryNo());
        assertThat(responseEntity.getBody().get(1).getUpperCategoryNo())
                .isEqualTo(testUpperCategoryList.get(1).getUpperCategoryNo());
    }

    @Test
    public void upperCategoryInsert_검증() {
        UpperCategoryInsertRequestDTO upperCategoryInsertRequestDTO =
                UpperCategoryInsertRequestDTO.builder()
                        .name("test")
                        .build();

        ResponseEntity<Long> responseEntity = testRestTemplate
                .postForEntity(UPPER_CATEGORY_URL,
                        upperCategoryInsertRequestDTO, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody())
                .isEqualTo(upperCategoryRepository.findAll().get(0).getUpperCategoryNo());
    }

    @Test
    public void upperCategoryUpdate_검증() {
        UpperCategory testUpperCategory = upperCategoryRepository.save(
                UpperCategory.builder()
                        .name("test")
                        .build());

        UpperCategoryUpdateRequestDTO upperCategoryUpdateRequestDTO =
                UpperCategoryUpdateRequestDTO.builder()
                        .upperCategoryNo(testUpperCategory.getUpperCategoryNo())
                        .name("update test")
                        .build();

        ResponseEntity<Long> responseEntity = testRestTemplate
                .exchange(UPPER_CATEGORY_URL, HttpMethod.PUT,
                        new HttpEntity<>(upperCategoryUpdateRequestDTO), Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody())
                .isEqualTo(testUpperCategory.getUpperCategoryNo());
        assertThat(upperCategoryRepository.findAll().get(0).getName())
                .isNotEqualTo(testUpperCategory.getName());
    }

    @Test
    public void upperCategoryDelete_검증() {
        Long testUpperCategoryNo = upperCategoryRepository.save(
                UpperCategory.builder()
                        .name("test")
                        .build()).getUpperCategoryNo();

        ResponseEntity<Void> responseEntity = testRestTemplate
                .exchange(UPPER_CATEGORY_URL + "?no=" + testUpperCategoryNo,
                        HttpMethod.DELETE, new HttpEntity<>(testUpperCategoryNo), Void.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNull();
        assertThat(upperCategoryRepository.findById(testUpperCategoryNo)).isEmpty();
    }

    @Test
    public void lowerCategorySelect_검증() {
        UpperCategory testUpperCategory = upperCategoryRepository.save(
                UpperCategory.builder()
                        .name("test")
                        .build());

        Long testLowerCategoryNo = lowerCategoryRepository.save(
                LowerCategory.builder()
                        .name("test")
                        .upperCategory(testUpperCategory)
                        .build()).getLowerCategoryNo();

        ResponseEntity<LowerCategoryVO> responseEntity = testRestTemplate
                .getForEntity(LOWER_CATEGORY_URL + "?no=" + testLowerCategoryNo,
                        LowerCategoryVO.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getLowerCategoryNo())
                .isEqualTo(testLowerCategoryNo);
        assertThat(responseEntity.getBody().getName())
                .isEqualTo(lowerCategoryRepository.findAll().get(0).getName());
    }

    @Test
    public void lowerCategorySelectAll_검증() {
        UpperCategory testUpperCategory = upperCategoryRepository.save(
                UpperCategory.builder()
                        .name("test")
                        .build());

        List<LowerCategory> testLowerCategoryList = new ArrayList<>();
        testLowerCategoryList.add(LowerCategory.builder()
                .upperCategory(testUpperCategory).name("test1").build());
        testLowerCategoryList.add(LowerCategory.builder()
                .upperCategory(testUpperCategory).name("test2").build());

        lowerCategoryRepository.saveAll(testLowerCategoryList);

        ResponseEntity<List<LowerCategoryVO>> responseEntity = testRestTemplate
                .exchange(LOWER_CATEGORY_URL + "/list", HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<LowerCategoryVO>>() {});

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().get(0).getLowerCategoryNo())
                .isEqualTo(testLowerCategoryList.get(0).getLowerCategoryNo());
        assertThat(responseEntity.getBody().get(1).getLowerCategoryNo())
                .isEqualTo(testLowerCategoryList.get(1).getLowerCategoryNo());
    }

    @Test
    public void lowerCategoryInsert_검증() {
        UpperCategory testUpperCategory = upperCategoryRepository.save(
                UpperCategory.builder()
                        .name("test")
                        .build());

        LowerCategoryInsertRequestDTO lowerCategoryInsertRequestDTO =
                LowerCategoryInsertRequestDTO.builder()
                        .upperCategory(testUpperCategory)
                        .name("test")
                        .build();

        ResponseEntity<Long> responseEntity = testRestTemplate
                .postForEntity(LOWER_CATEGORY_URL,
                        lowerCategoryInsertRequestDTO, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody())
                .isEqualTo(lowerCategoryRepository.findAll().get(0).getLowerCategoryNo());
    }

    @Test
    public void lowerCategoryUpdate_검증() {
        UpperCategory testUpperCategory = upperCategoryRepository.save(
                UpperCategory.builder()
                        .name("test")
                        .build());

        LowerCategory testLowerCategory = lowerCategoryRepository.save(
                LowerCategory.builder()
                        .upperCategory(testUpperCategory)
                        .name("test")
                        .build());

        LowerCategoryUpdateRequestDTO lowerCategoryUpdateRequestDTO =
                LowerCategoryUpdateRequestDTO.builder()
                        .lowerCategoryNo(testLowerCategory.getLowerCategoryNo())
                        .upperCategory(testUpperCategory)
                        .name("update test")
                        .build();

        ResponseEntity<Long> responseEntity = testRestTemplate
                .exchange(LOWER_CATEGORY_URL, HttpMethod.PUT,
                        new HttpEntity<>(lowerCategoryUpdateRequestDTO), Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody())
                .isEqualTo(testLowerCategory.getLowerCategoryNo());
        assertThat(lowerCategoryRepository.findAll().get(0).getName())
                .isNotEqualTo(testLowerCategory.getName());
    }

    @Test
    public void lowerCategoryDelete_검증() {
        UpperCategory testUpperCategory = upperCategoryRepository.save(
                UpperCategory.builder()
                        .name("test")
                        .build());

        Long testLowerCategoryNo = lowerCategoryRepository.save(
                LowerCategory.builder()
                        .upperCategory(testUpperCategory)
                        .name("test")
                        .build()).getLowerCategoryNo();

        ResponseEntity<Void> responseEntity = testRestTemplate
                .exchange(LOWER_CATEGORY_URL + "?no=" + testLowerCategoryNo,
                        HttpMethod.DELETE, new HttpEntity<>(testLowerCategoryNo), Void.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNull();
        assertThat(lowerCategoryRepository.findById(testLowerCategoryNo)).isEmpty();
    }
}
