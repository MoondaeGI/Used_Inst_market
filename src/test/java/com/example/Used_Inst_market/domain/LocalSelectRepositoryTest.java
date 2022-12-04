package com.example.Used_Inst_market.domain;

import com.example.Used_Inst_market.domain.address.Address;
import com.example.Used_Inst_market.domain.address.AddressRepository;
import com.example.Used_Inst_market.domain.city.City;
import com.example.Used_Inst_market.domain.city.CityRepository;
import com.example.Used_Inst_market.domain.local.Local;
import com.example.Used_Inst_market.domain.local.LocalRepository;
import com.example.Used_Inst_market.domain.category.brand.Brand;
import com.example.Used_Inst_market.domain.category.brand.BrandRepository;
import com.example.Used_Inst_market.domain.category.lower.LowerCategory;
import com.example.Used_Inst_market.domain.category.lower.LowerCategoryRepository;
import com.example.Used_Inst_market.domain.category.upper.UpperCategory;
import com.example.Used_Inst_market.domain.category.upper.UpperCategoryRepository;
import com.example.Used_Inst_market.domain.post.Post;
import com.example.Used_Inst_market.domain.post.PostRepository;
import com.example.Used_Inst_market.domain.select.localselect.LocalSelect;
import com.example.Used_Inst_market.domain.select.localselect.LocalSelectRepository;
import com.example.Used_Inst_market.domain.user.User;
import com.example.Used_Inst_market.domain.user.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LocalSelectRepositoryTest {
    @Autowired
    private PostRepository postRepository;

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
    public void setup_검증() {
        System.out.println("test");
    }

    @Test
    public void TB_POST_삽입검증() {
        String testTitle = "test";

        Post testPost = postRepository.save(
                Post.builder()
                        .title(testTitle)
                        .content("test")
                        .price(1)
                        .user(userRepository.findAll().get(0))
                        .build());

        LocalSelect testLocalSelect = localSelectRepository.save(
                LocalSelect.builder()
                        .post(testPost)
                        .local(localRepository.findAll().get(0))
                        .city(cityRepository.findAll().get(0))
                        .build());

        assertThat(postRepository.findAll().get(0).getPostNo())
                .isEqualTo(testPost.getPostNo());
        assertThat(localSelectRepository.findAll().get(0).getLocalSelectNo())
                .isEqualTo(testLocalSelect.getLocalSelectNo());
    }

    @Test
    public void findByPost_검증() {
        Post testPost = postRepository.save(
                Post.builder()
                        .title("test")
                        .content("test")
                        .price(1)
                        .user(userRepository.findAll().get(0))
                        .build());

        localSelectRepository.save(
                LocalSelect.builder()
                        .post(testPost)
                        .local(localRepository.findAll().get(0))
                        .city(cityRepository.findAll().get(0))
                        .build());

        assertThat(postRepository.findAll().get(0).getPostNo())
                .isEqualTo(localSelectRepository
                        .findByPost(testPost).getPost().getPostNo());
    }

    @Test
    public void findByLocal_검증() {
        Post testPost = postRepository.save(
                Post.builder()
                        .title("test")
                        .content("test")
                        .price(1)
                        .user(userRepository.findAll().get(0))
                        .build());

        localSelectRepository.save(
                LocalSelect.builder()
                        .post(testPost)
                        .local(localRepository.findAll().get(0))
                        .city(cityRepository.findAll().get(0))
                        .build());

        assertThat(postRepository.findAll().get(0).getPostNo())
                .isEqualTo(localSelectRepository
                        .findByLocal(localRepository.findAll().get(0))
                        .get(0)
                        .getPostNo());
    }
}
