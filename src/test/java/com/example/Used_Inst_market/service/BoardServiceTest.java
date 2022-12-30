package com.example.Used_Inst_market.service;

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
import com.example.Used_Inst_market.model.domain.select.categoryselect.CategorySelect;
import com.example.Used_Inst_market.model.domain.select.categoryselect.CategorySelectRepository;
import com.example.Used_Inst_market.model.domain.select.localselect.LocalSelect;
import com.example.Used_Inst_market.model.domain.select.localselect.LocalSelectRepository;
import com.example.Used_Inst_market.model.domain.user.Role;
import com.example.Used_Inst_market.model.domain.user.User;
import com.example.Used_Inst_market.model.domain.user.UserRepository;
import com.example.Used_Inst_market.model.vo.board.PostVO;
import com.example.Used_Inst_market.service.board.BoardService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardServiceTest {
    @Autowired private BoardService boardService;
    @Autowired private UserRepository userRepository;
    @Autowired private PostRepository postRepository;
    @Autowired private CategorySelectRepository categorySelectRepository;
    @Autowired private UpperCategoryRepository upperCategoryRepository;
    @Autowired private LowerCategoryRepository lowerCategoryRepository;
    @Autowired private BrandRepository brandRepository;
    @Autowired private LocalSelectRepository localSelectRepository;
    @Autowired private UpperLocalRepository upperLocalRepository;
    @Autowired private LowerLocalRepository lowerLocalRepository;

    @Before
    public void setup() {
        User user = userRepository.save(
                User.builder()
                        .name("test")
                        .email("test1234@test.com")
                        .picture("test_image.jpg")
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

        Brand brand = brandRepository.save(
                Brand.builder()
                        .name("test")
                        .lowerCategory(lowerCategory)
                        .build());

        UpperLocal upperLocal = upperLocalRepository.save(
                UpperLocal.builder()
                        .name("test")
                        .build());

        LowerLocal lowerLocal = lowerLocalRepository.save(
                LowerLocal.builder()
                        .name("test")
                        .upperLocal(upperLocal)
                        .build());

        Post post = postRepository.save(
                Post.builder()
                        .title("test")
                        .content("test")
                        .price(1)
                        .user(user)
                        .build());

        categorySelectRepository.save(
                CategorySelect.builder()
                        .post(post)
                        .upperCategory(upperCategory)
                        .lowerCategory(lowerCategory)
                        .brand(brand)
                        .build());

        localSelectRepository.save(
                LocalSelect.builder()
                        .post(post)
                        .upperLocal(upperLocal)
                        .lowerLocal(lowerLocal)
                        .build());
    }

    @After
    public void teardown() {
        userRepository.deleteAll();
        upperCategoryRepository.deleteAll();
        upperLocalRepository.deleteAll();
    }

    @Test
    public void selectAll_검증() {
        List<PostVO> postList = boardService.selectAll();

        assertThat(postList.get(0).getPostNo())
                .isEqualTo(postRepository.findAll().get(0).getPostNo());
    }
}
