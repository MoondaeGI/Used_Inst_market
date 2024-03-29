package com.example.Used_Inst_market.domain;

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
import com.example.Used_Inst_market.util.enums.Role;
import com.example.Used_Inst_market.model.domain.user.User;
import com.example.Used_Inst_market.model.domain.user.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostRepositoryTest {
    @Autowired private PostRepository postRepository;
    @Autowired private UpperCategoryRepository upperCategoryRepository;
    @Autowired private LowerCategoryRepository lowerCategoryRepository;
    @Autowired private BrandRepository brandRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private UpperLocalRepository upperLocalRepository;
    @Autowired private LowerLocalRepository lowerLocalRepository;

    @Before
    public void setup() {
        UpperCategory testUpperCategory = upperCategoryRepository.save(
                UpperCategory.builder()
                        .name("test")
                        .build());

        LowerCategory testLowerCategory = lowerCategoryRepository.save(
                LowerCategory.builder()
                        .upperCategory(testUpperCategory)
                        .name("test")
                        .build());

        brandRepository.save(
                Brand.builder()
                        .lowerCategory(testLowerCategory)
                        .name("test")
                        .build());

        UpperLocal testUpperLocal = upperLocalRepository.save(
                UpperLocal.builder()
                        .name("test")
                        .build());

        lowerLocalRepository.save(
                LowerLocal.builder()
                        .upperLocal(testUpperLocal)
                        .name("test")
                        .build());

        userRepository.save(
                User.builder()
                        .name("test")
                        .email("test1234@test.com")
                        .picture("testPicture")
                        .role(Role.ADMIN)
                        .build());
    }

    @After
    public void teardown() {
        postRepository.deleteAll();
        userRepository.deleteAll();
        upperLocalRepository.deleteAll();
        upperCategoryRepository.deleteAll();
    }

    @Test
    public void TB_POST_삽입검증() {
        String testTitle = "test";

        Long testPostNo = postRepository.save(
                Post.builder()
                        .title(testTitle)
                        .content("test")
                        .price(1)
                        .user(userRepository.findAll().get(0))
                        .build())
                .getPostNo();

        assertThat(postRepository.findAll().get(0).getPostNo()).isEqualTo(testPostNo);
        assertThat(postRepository.findAll().get(0).getTitle()).isEqualTo(testTitle);
    }

    @Test(expected = ConstraintViolationException.class)
    public void price_예외값_검증() {
        Integer testPrice = -1;

        postRepository.save(
                Post.builder()
                        .title("test")
                        .content("test")
                        .price(testPrice)
                        .user(userRepository.findAll().get(0))
                        .build());
    }
}
