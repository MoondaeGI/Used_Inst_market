package com.example.Used_Inst_market.web;

import com.example.Used_Inst_market.model.domain.category.brand.Brand;
import com.example.Used_Inst_market.model.domain.category.brand.BrandRepository;
import com.example.Used_Inst_market.model.domain.category.lower.LowerCategory;
import com.example.Used_Inst_market.model.domain.category.lower.LowerCategoryRepository;
import com.example.Used_Inst_market.model.domain.category.upper.UpperCategory;
import com.example.Used_Inst_market.model.domain.category.upper.UpperCategoryRepository;
import com.example.Used_Inst_market.model.vo.category.BrandVO;
import com.example.Used_Inst_market.model.vo.category.LowerCategoryVO;
import com.example.Used_Inst_market.model.vo.category.UpperCategoryVO;
import com.example.Used_Inst_market.web.dto.category.brand.BrandInsertDTO;
import com.example.Used_Inst_market.web.dto.category.lower.LowerCategoryInsertDTO;
import com.example.Used_Inst_market.web.dto.category.upper.UpperCategoryInsertDTO;
import com.example.Used_Inst_market.web.dto.category.upper.UpperCategoryUpdateDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryControllerTest {
    private static final String UPPER_CATEGORY_URL = "/category/upper/info";

    @Autowired private WebApplicationContext webApplicationContext;
    @Autowired private UpperCategoryRepository upperCategoryRepository;
    @Autowired private LowerCategoryRepository lowerCategoryRepository;
    @Autowired private BrandRepository brandRepository;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                        .webAppContextSetup(webApplicationContext)
                        .apply(springSecurity())
                .build();

        UpperCategory upperCategory = upperCategoryRepository.save(
                UpperCategory.builder()
                        .name("test")
                        .build());

        LowerCategory lowerCategory = lowerCategoryRepository.save(
                LowerCategory.builder()
                        .name("test")
                        .upperCategory(upperCategory)
                        .build());

        brandRepository.save(
                Brand.builder()
                        .name("test")
                        .lowerCategory(lowerCategory)
                        .build());
    }

    @After
    public void teardown() {
        upperCategoryRepository.deleteAll();
    }

    @WithMockUser(roles = "USER")
    @Test
    public void upperCategorySelect_검증() throws Exception {
        final Long upperCategoryNo = upperCategoryRepository.findAll().get(0)
                .getUpperCategoryNo();

        mockMvc.perform(get(UPPER_CATEGORY_URL + "?no=" + upperCategoryNo))
                .andExpect(status().isOk());
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    public void upperCategoryInsert_검증() throws Exception {
        String testName = "insert test";

        UpperCategoryInsertDTO upperCategoryInsertDTO = UpperCategoryInsertDTO.builder()
                .name(testName)
                .build();

        mockMvc.perform(post(UPPER_CATEGORY_URL)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(new ObjectMapper().writeValueAsString(upperCategoryInsertDTO)))
                .andExpect(status().isOk());

        assertThat(upperCategoryRepository.findAll().get(1).getName())
                .isEqualTo(testName);
    }

    @WithAnonymousUser
    @Test
    public void upperCategoryInsert_권한실패검증() throws Exception {
        UpperCategoryInsertDTO upperCategoryInsertDTO = UpperCategoryInsertDTO.builder()
                .name("insert test")
                .build();

        mockMvc.perform(post(UPPER_CATEGORY_URL)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(new ObjectMapper().writeValueAsString(upperCategoryInsertDTO)))
                .andExpect(status().is(302));
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    public void upperCategoryUpdate_검증() throws Exception {
        String updateName = "update test";
        final Long upperCategoryNo = upperCategoryRepository.findAll().get(0)
                .getUpperCategoryNo();

        UpperCategoryUpdateDTO upperCategoryUpdateDTO = UpperCategoryUpdateDTO.builder()
                .upperCategoryNo(upperCategoryNo)
                .name(updateName)
                .build();

        mockMvc.perform(put(UPPER_CATEGORY_URL)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(new ObjectMapper().writeValueAsString(upperCategoryUpdateDTO)))
                .andExpect(status().isOk());

        assertThat(upperCategoryRepository.findAll().get(0).getName())
                .isEqualTo(updateName);
    }
}
