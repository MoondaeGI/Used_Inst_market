package com.example.Used_Inst_market.service;

import com.example.Used_Inst_market.model.domain.address.Address;
import com.example.Used_Inst_market.model.domain.address.AddressRepository;
import com.example.Used_Inst_market.model.domain.city.City;
import com.example.Used_Inst_market.model.domain.city.CityRepository;
import com.example.Used_Inst_market.model.domain.local.Local;
import com.example.Used_Inst_market.model.domain.local.LocalRepository;
import com.example.Used_Inst_market.model.domain.select.categoryselect.CategorySelect;
import com.example.Used_Inst_market.model.domain.select.localselect.LocalSelect;
import com.example.Used_Inst_market.model.domain.select.localselect.LocalSelectRepository;
import com.example.Used_Inst_market.model.domain.category.brand.Brand;
import com.example.Used_Inst_market.model.domain.category.brand.BrandRepository;
import com.example.Used_Inst_market.model.domain.select.categoryselect.CategorySelectRepository;
import com.example.Used_Inst_market.model.domain.category.lower.LowerCategory;
import com.example.Used_Inst_market.model.domain.category.lower.LowerCategoryRepository;
import com.example.Used_Inst_market.model.domain.category.upper.UpperCategory;
import com.example.Used_Inst_market.model.domain.category.upper.UpperCategoryRepository;
import com.example.Used_Inst_market.model.domain.board.post.Post;
import com.example.Used_Inst_market.model.domain.board.post.PostRepository;
import com.example.Used_Inst_market.model.domain.board.post.SoldYN;
import com.example.Used_Inst_market.model.domain.user.User;
import com.example.Used_Inst_market.model.domain.user.UserRepository;
import com.example.Used_Inst_market.service.board.PostService;
import com.example.Used_Inst_market.web.dto.board.post.PostDeleteRequestDTO;
import com.example.Used_Inst_market.web.dto.board.post.PostInsertRequestDTO;
import com.example.Used_Inst_market.web.dto.board.post.PostSelectRequestDTO;
import com.example.Used_Inst_market.web.dto.board.post.PostUpdateRequestDTO;
import com.example.Used_Inst_market.model.vo.board.PostVO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostServiceTest {
    @Autowired private PostService postService;

    @Autowired private PostRepository postRepository;

    @Autowired private CategorySelectRepository categorySelectRepository;
    @Autowired private UpperCategoryRepository upperCategoryRepository;
    @Autowired private LowerCategoryRepository lowerCategoryRepository;
    @Autowired private BrandRepository brandRepository;

    @Autowired private LocalSelectRepository localSelectRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private AddressRepository addressRepository;
    @Autowired private LocalRepository localRepository;
    @Autowired private CityRepository cityRepository;

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

    @Test
    public void select_검증() {
        String testString = "test";

        Post testPost = postRepository.save(
                Post.builder()
                        .title(testString)
                        .content(testString)
                        .price(10000)
                        .user(userRepository.findAll().get(0))
                        .build());

        categorySelectRepository.save(
                CategorySelect.builder()
                        .post(testPost)
                        .upperCategory(upperCategoryRepository.findAll().get(0))
                        .lowerCategory(lowerCategoryRepository.findAll().get(0))
                        .brand(brandRepository.findAll().get(0))
                        .build());

        localSelectRepository.save(
                LocalSelect.builder()
                        .post(testPost)
                        .local(localRepository.findAll().get(0))
                        .city(cityRepository.findAll().get(0))
                        .build());

        PostSelectRequestDTO postSelectRequestDTO =
                PostSelectRequestDTO.builder()
                        .postNo(testPost.getPostNo())
                        .build();

        PostVO testPostVO = postService.select(postSelectRequestDTO);

        assertThat(testPostVO.getTitle()).isEqualTo(testString);
        assertThat(testPostVO.getContent()).isEqualTo(testString);
        assertThat(testPostVO.getSoldYN()).isEqualTo(SoldYN.SALE);

        assertThat(testPostVO.getPostNo())
                .isEqualTo(categorySelectRepository
                        .findByPost(testPost).getPost().getPostNo());

        assertThat(testPostVO.getPostNo())
                .isEqualTo(localSelectRepository
                        .findByPost(testPost).getPost().getPostNo());
    }

    @Test
    public void selectAll_검증() {
        List<Post> testPosts = new ArrayList<>();

        testPosts.add(Post.builder()
                .title("test1")
                .content("test1")
                .price(1)
                .user(userRepository.findAll().get(0))
                .build());
        testPosts.add(Post.builder()
                .title("test2")
                .content("test2")
                .price(2)
                .user(userRepository.findAll().get(0))
                .build());

        postRepository.saveAll(testPosts);

        List<PostVO> testPostVOs = postService.selectAll();

        assertThat(testPostVOs.get(0).getPostNo()).isEqualTo(testPosts.get(0).getPostNo());
        assertThat(testPostVOs.get(0).getPrice()).isEqualTo(1);
        assertThat(testPostVOs.get(1).getPostNo()).isEqualTo(testPosts.get(1).getPostNo());
        assertThat(testPostVOs.get(1).getPrice()).isEqualTo(2);
    }

    @Test
    public void insert_검증() {
        PostInsertRequestDTO postInsertRequestDTO =
                PostInsertRequestDTO.builder()
                        .userNo(userRepository.findAll().get(0).getUserNo())
                        .title("test")
                        .content("test")
                        .price(1)
                        .upperCategoryNo(upperCategoryRepository
                                .findAll().get(0).getUpperCategoryNo())
                        .lowerCategoryNo(lowerCategoryRepository
                                .findAll().get(0).getLowerCategoryNo())
                        .brandNo(brandRepository.findAll().get(0).getBrandNo())
                        .build();

        Long testPostNo = postService.insert(postInsertRequestDTO);

        assertThat(postRepository.findAll().get(0).getPostNo()).isEqualTo(testPostNo);
        assertThat(postRepository.findAll().get(0).getSoldYN()).isEqualTo(SoldYN.SALE);

        assertThat(categorySelectRepository.findAll().get(0).getPost().getPostNo())
                .isEqualTo(testPostNo);
        assertThat(localSelectRepository.findAll().get(0).getPost().getPostNo())
                .isEqualTo(testPostNo);
    }

    @Test
    public void update_검증() {
        String updateTitle = "update title";
        String updateContent = "update content";
        Integer updatePrice = 10;

        UpperCategory updateUpperCategory = upperCategoryRepository.save(
                UpperCategory.builder()
                        .name("update name")
                        .build());

        LowerCategory updateLowerCategory = lowerCategoryRepository.save(
                LowerCategory.builder()
                        .upperCategory(updateUpperCategory)
                        .name("update name")
                        .build());

        Brand updateBrand = brandRepository.save(
                Brand.builder()
                        .lowerCategory(updateLowerCategory)
                        .name("update name")
                        .build());

        Long testPostNo = postService.insert(PostInsertRequestDTO.builder()
                .title("test")
                .content("test")
                .price(1)
                .userNo(userRepository.findAll().get(0).getUserNo())
                .upperCategoryNo(upperCategoryRepository
                        .findAll().get(0).getUpperCategoryNo())
                .lowerCategoryNo(lowerCategoryRepository
                        .findAll().get(0).getLowerCategoryNo())
                .brandNo(brandRepository.findAll().get(0).getBrandNo())
                .build());

        PostUpdateRequestDTO postUpdateRequestDTO =
                PostUpdateRequestDTO.builder()
                        .postNo(testPostNo)
                        .title(updateTitle)
                        .content(updateContent)
                        .price(updatePrice)
                        .soldYN(SoldYN.SOLD_OUT)
                        .upperCategoryNo(updateUpperCategory
                                .getUpperCategoryNo())
                        .lowerCategoryNo(updateLowerCategory
                                .getLowerCategoryNo())
                        .brandNo(updateBrand.getBrandNo())
                        .build();

        Long updatePostNo = postService.update(postUpdateRequestDTO);

        assertThat(updatePostNo).isEqualTo(testPostNo);
        assertThat(postRepository.findAll().get(0).getTitle()).isEqualTo(updateTitle);
        assertThat(postRepository.findAll().get(0).getContent()).isEqualTo(updateContent);
        assertThat(postRepository.findAll().get(0).getPrice()).isEqualTo(updatePrice);
        assertThat(postRepository.findAll().get(0).getSoldYN()).isEqualTo(SoldYN.SOLD_OUT);

        assertThat(categorySelectRepository.findAll().get(0)
                .getUpperCategory().getUpperCategoryNo())
                .isEqualTo(updateUpperCategory.getUpperCategoryNo());
        assertThat(categorySelectRepository.findAll().get(0)
                .getLowerCategory().getLowerCategoryNo())
                .isEqualTo(updateLowerCategory.getLowerCategoryNo());
        assertThat(categorySelectRepository.findAll().get(0)
                .getBrand().getBrandNo())
                .isEqualTo(updateBrand.getBrandNo());
    }

    @Test
    public void delete_검증() {
        Long testPostNo = postService.insert(PostInsertRequestDTO.builder()
                .title("test")
                .content("test")
                .price(1)
                .userNo(userRepository.findAll().get(0).getUserNo())
                .upperCategoryNo(upperCategoryRepository
                        .findAll().get(0).getUpperCategoryNo())
                .lowerCategoryNo(lowerCategoryRepository
                        .findAll().get(0).getLowerCategoryNo())
                .brandNo(brandRepository.findAll().get(0).getBrandNo())
                .build());

        PostDeleteRequestDTO postDeleteRequestDTO =
                PostDeleteRequestDTO.builder()
                        .postNo(testPostNo)
                        .build();

        postService.delete(postDeleteRequestDTO);

        assertThat(postRepository.findById(testPostNo)).isEmpty();

        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> categorySelectRepository.findAll().get(0));
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> localSelectRepository.findAll().get(0));
    }
}
