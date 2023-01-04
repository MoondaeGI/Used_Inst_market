package com.example.Used_Inst_market.web;

import com.example.Used_Inst_market.model.domain.board.picture.PictureRepository;
import com.example.Used_Inst_market.model.domain.board.post.Post;
import com.example.Used_Inst_market.model.domain.board.post.PostRepository;
import com.example.Used_Inst_market.model.domain.category.brand.Brand;
import com.example.Used_Inst_market.model.domain.category.brand.BrandRepository;
import com.example.Used_Inst_market.model.domain.category.lower.LowerCategory;
import com.example.Used_Inst_market.model.domain.category.lower.LowerCategoryRepository;
import com.example.Used_Inst_market.model.domain.category.upper.UpperCategory;
import com.example.Used_Inst_market.model.domain.category.upper.UpperCategoryRepository;
import com.example.Used_Inst_market.model.domain.local.lower.LowerLocal;
import com.example.Used_Inst_market.model.domain.local.lower.LowerLocalRepository;
import com.example.Used_Inst_market.model.domain.local.upper.UpperLocal;
import com.example.Used_Inst_market.model.domain.local.upper.UpperLocalRepository;
import com.example.Used_Inst_market.model.domain.user.Role;
import com.example.Used_Inst_market.model.domain.user.User;
import com.example.Used_Inst_market.model.domain.user.UserRepository;
import com.example.Used_Inst_market.model.dto.board.post.PostInsertDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockPart;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostControllerTest {
    private static final String POST_URL = "/post/info";

    @Autowired private WebApplicationContext webApplicationContext;
    @Autowired private PostRepository postRepository;
    @Autowired private PictureRepository pictureRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private UpperCategoryRepository upperCategoryRepository;
    @Autowired private LowerCategoryRepository lowerCategoryRepository;
    @Autowired private BrandRepository brandRepository;
    @Autowired private UpperLocalRepository upperLocalRepository;
    @Autowired private LowerLocalRepository lowerLocalRepository;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();

        userRepository.save(
                User.builder()
                        .name("test")
                        .picture("test")
                        .email("test1234@test.com")
                        .role(Role.ADMIN)
                        .build());

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

        UpperLocal upperLocal = upperLocalRepository.save(
                UpperLocal.builder()
                        .name("test")
                        .build());

        lowerLocalRepository.save(
                LowerLocal.builder()
                        .name("test")
                        .upperLocal(upperLocal)
                        .build());
    }

    @After
    public void teardown() {
        userRepository.deleteAll();
        upperCategoryRepository.deleteAll();
        upperLocalRepository.deleteAll();
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    public void select_검증() throws Exception {
        postRepository.save(
                Post.builder()
                        .title("test")
                        .content("test")
                        .price(1)
                        .user(userRepository.findAll().get(0))
                        .build());

        final Long postNo = postRepository.findAll().get(0).getPostNo();

        mockMvc.perform(get(POST_URL + "?no=" + postNo))
                .andExpect(status().isOk());
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    public void insert_검증() throws Exception {
        final String pictureName = "test_image.png";

        MockMultipartFile imageFile = new MockMultipartFile(
                "images",
                pictureName,
                "image/png",
                "<<png data>>".getBytes());

        PostInsertDTO postInsertDTO = PostInsertDTO.builder()
                .title("test")
                .content("test")
                .price(1)
                .upperCategoryNo(
                        upperCategoryRepository.findAll().get(0).getUpperCategoryNo())
                .lowerCategoryNo(
                        lowerCategoryRepository.findAll().get(0).getLowerCategoryNo())
                .brandNo(
                        brandRepository.findAll().get(0).getBrandNo())
                .upperLocalNo(
                        upperLocalRepository.findAll().get(0).getUpperLocalNo())
                .lowerLocalNo(
                        lowerLocalRepository.findAll().get(0).getLowerLocalNo())
                .userNo(
                        userRepository.findAll().get(0).getUserNo())
                .build();

        mockMvc.perform(multipart(POST_URL)
                        .file(imageFile)
                        .part(new MockPart("dto", new ObjectMapper().writeValueAsBytes(postInsertDTO))))
                .andExpect(status().isOk());
    }
}
