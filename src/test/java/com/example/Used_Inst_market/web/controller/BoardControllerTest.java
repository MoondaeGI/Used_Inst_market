package com.example.Used_Inst_market.web.controller;

import com.example.Used_Inst_market.model.domain.local.lower.LowerLocalRepository;
import com.example.Used_Inst_market.model.domain.local.upper.UpperLocalRepository;
import com.example.Used_Inst_market.model.domain.category.brand.BrandRepository;
import com.example.Used_Inst_market.model.domain.category.lower.LowerCategoryRepository;
import com.example.Used_Inst_market.model.domain.category.upper.UpperCategoryRepository;
import com.example.Used_Inst_market.model.domain.board.post.Post;
import com.example.Used_Inst_market.model.domain.board.post.PostRepository;
import com.example.Used_Inst_market.model.domain.select.categoryselect.CategorySelect;
import com.example.Used_Inst_market.model.domain.select.categoryselect.CategorySelectRepository;
import com.example.Used_Inst_market.model.domain.select.localselect.LocalSelect;
import com.example.Used_Inst_market.model.domain.select.localselect.LocalSelectRepository;
import com.example.Used_Inst_market.model.domain.user.UserRepository;
import com.example.Used_Inst_market.web.dto.board.post.PostInsertRequestDTO;
import com.example.Used_Inst_market.model.vo.board.PostVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BoardControllerTest {
    private static final String URL = "/post/info";
    private static final String CT_SELECT_URL = "/post/info/category";
    private static final String LO_SELECT_URL = "/post/info/local";

    @Autowired private TestRestTemplate testRestTemplate;

    @Autowired private PostRepository postRepository;

    @Autowired private CategorySelectRepository categorySelectRepository;
    @Autowired private UpperCategoryRepository upperCategoryRepository;
    @Autowired private LowerCategoryRepository lowerCategoryRepository;
    @Autowired private BrandRepository brandRepository;

    @Autowired private LocalSelectRepository localSelectRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private UpperLocalRepository upperLocalRepository;
    @Autowired private LowerLocalRepository lowerLocalRepository;
/*
    @Before
    public void setup() {
        UpperCategory testUpperCategory = upperCategoryRepository
                .save(UpperCategory.builder().name("test").build());

        LowerCategory testLowerCategory = lowerCategoryRepository
                .save(LowerCategory.builder()
                        .upperCategory(testUpperCategory)
                        .name("test")
                        .build());

        brandRepository.save(Brand.builder()
                .lowerCategory(testLowerCategory)
                .name("test")
                .build());

        Local testLocal = localRepository.save(
                Local.builder()
                        .name("test")
                        .build());

        City testCity = cityRepository.save(
                City.builder()
                        .local(testLocal)
                        .name("test")
                        .build());

        User testUser = userRepository.save(User.builder()
                .name("test")
                .email("test1234@test.com")
                .phoneNumber("010-0000-0000")
                .build());

        addressRepository.save(
                Address.builder()
                        .user(testUser)
                        .local(testLocal)
                        .city(testCity)
                        .addressDetail("test")
                        .build());
    }

    @After
    public void teardown() {
        postRepository.deleteAll();

        userRepository.deleteAll();
        localRepository.deleteAll();
        upperCategoryRepository.deleteAll();
    }
*/
    @Test
    public void setup_검증() {
        System.out.println("test");
    }

    @Test
    public void select_검증() {
        ResponseEntity<PostVO> responseEntity = testRestTemplate
                .getForEntity(URL + "?no=91", PostVO.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void selectAll_검증() {
        Post testPost = postRepository.save(
                Post.builder()
                        .title("test")
                        .content("test")
                        .price(1)
                        .user(userRepository.findAll().get(0))
                        .build());

        CategorySelect testCategorySelect = categorySelectRepository.save(
                CategorySelect.builder()
                        .post(testPost)
                        .upperCategory(upperCategoryRepository.findAll().get(0))
                        .lowerCategory(lowerCategoryRepository.findAll().get(0))
                        .brand(brandRepository.findAll().get(0))
                        .build());

        LocalSelect testLocalSelect = localSelectRepository.save(
                LocalSelect.builder()
                        .post(testPost)
                        .upperLocal(upperLocalRepository.findAll().get(0))
                        .lowerLocal(lowerLocalRepository.findAll().get(0))
                        .build());

        ResponseEntity<List<PostVO>> responseEntity = testRestTemplate
                .exchange(URL + "/list", HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<PostVO>>() {});

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().get(0).getPostNo())
                .isEqualTo(postRepository.findAll().get(0).getPostNo());

        assertThat(categorySelectRepository.findByPost(testPost).getCategorySelectNo())
                .isEqualTo(testCategorySelect.getCategorySelectNo());
        assertThat(localSelectRepository.findByPost(testPost).getLocalSelectNo())
                .isEqualTo(testLocalSelect.getLocalSelectNo());
    }

    @Test
    public void insert_검증() {
        String testTitle = "test";

        PostInsertRequestDTO postInsertRequestDTO =
                PostInsertRequestDTO.builder()
                        .title(testTitle)
                        .content("test")
                        .price(1)
                        .userNo(userRepository.findAll().get(0).getUserNo())
                        .upperCategoryNo(upperCategoryRepository
                                .findAll().get(0).getUpperCategoryNo())
                        .lowerCategoryNo(lowerCategoryRepository
                                .findAll().get(0).getLowerCategoryNo())
                        .brandNo(brandRepository.findAll().get(0).getBrandNo())
                        .build();

        ResponseEntity<Long> responseEntity = testRestTemplate
                .postForEntity(URL, postInsertRequestDTO, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody())
                .isEqualTo(postRepository.findAll().get(0).getPostNo());
    }
}
