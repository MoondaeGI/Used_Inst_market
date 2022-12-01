package com.example.Used_Inst_market.service;

import com.example.Used_Inst_market.domain.address.addressdetail.Address;
import com.example.Used_Inst_market.domain.address.addressdetail.AddressRepository;
import com.example.Used_Inst_market.domain.address.city.City;
import com.example.Used_Inst_market.domain.address.city.CityRepository;
import com.example.Used_Inst_market.domain.address.local.Local;
import com.example.Used_Inst_market.domain.address.local.LocalRepository;
import com.example.Used_Inst_market.domain.category.brand.Brand;
import com.example.Used_Inst_market.domain.category.brand.BrandRepository;
import com.example.Used_Inst_market.domain.category.lower.LowerCategory;
import com.example.Used_Inst_market.domain.category.lower.LowerCategoryRepository;
import com.example.Used_Inst_market.domain.category.upper.UpperCategory;
import com.example.Used_Inst_market.domain.category.upper.UpperCategoryRepository;
import com.example.Used_Inst_market.domain.post.PostRepository;
import com.example.Used_Inst_market.domain.user.User;
import com.example.Used_Inst_market.domain.user.UserRepository;
import com.example.Used_Inst_market.service.category.CategorySelectService;
import com.example.Used_Inst_market.service.post.PostService;
import com.example.Used_Inst_market.web.dto.category.categoryselect.SelectFromBrandRequestDTO;
import com.example.Used_Inst_market.web.dto.category.categoryselect.SelectFromLowerRequestDTO;
import com.example.Used_Inst_market.web.dto.category.categoryselect.SelectFromUpperRequestDTO;
import com.example.Used_Inst_market.web.dto.post.PostInsertRequestDTO;
import com.example.Used_Inst_market.web.vo.post.PostVO;
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

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategorySelectServiceTest {
    @Autowired private CategorySelectService categorySelectService;
    @Autowired private PostService postService;

    @Autowired private PostRepository postRepository;
    @Autowired private UpperCategoryRepository upperCategoryRepository;
    @Autowired private LowerCategoryRepository lowerCategoryRepository;
    @Autowired private BrandRepository brandRepository;

    @Autowired private LocalRepository localRepository;
    @Autowired private CityRepository cityRepository;
    @Autowired private AddressRepository addressRepository;
    @Autowired private UserRepository userRepository;

    @Before
    public void setup() {
        Local testLocal = localRepository.save(Local.builder()
                .name("test").build());

        City testCity = cityRepository.save(City.builder()
                .local(testLocal).name("test").build());

        User testUser = userRepository.save(
                User.builder()
                        .name("test")
                        .email("test1234@naver.com")
                        .phoneNumber("010-0000-0000")
                        .build());

        addressRepository.save(
                Address.builder()
                        .user(testUser)
                        .local(testLocal)
                        .city(testCity)
                        .addressDetail("test")
                        .build());

        List<UpperCategory> testUpperCategoryList = new ArrayList<>();
        testUpperCategoryList.add(
                UpperCategory.builder().name("test1").build());
        testUpperCategoryList.add(
                UpperCategory.builder().name("test2").build());

        upperCategoryRepository.saveAll(testUpperCategoryList);

        List<LowerCategory> testLowerCategoryList = new ArrayList<>();
        testLowerCategoryList.add(
                LowerCategory.builder()
                        .upperCategory(testUpperCategoryList.get(0))
                        .name("test1")
                        .build());
        testLowerCategoryList.add(LowerCategory.builder()
                .upperCategory(testUpperCategoryList.get(0))
                .name("test2")
                .build());
        testLowerCategoryList.add(LowerCategory.builder()
                .upperCategory(testUpperCategoryList.get(1))
                .name("test3")
                .build());

        lowerCategoryRepository.saveAll(testLowerCategoryList);

        List<Brand> testBrandList = new ArrayList<>();
        testBrandList.add(Brand.builder()
                .lowerCategory(testLowerCategoryList.get(0))
                .name("test1")
                .build());
        testBrandList.add(Brand.builder()
                .lowerCategory(testLowerCategoryList.get(0))
                .name("test2")
                .build());
        testBrandList.add(Brand.builder()
                .lowerCategory(testLowerCategoryList.get(1))
                .name("test3")
                .build());
        testBrandList.add(Brand.builder()
                .lowerCategory(testLowerCategoryList.get(2))
                .name("test4")
                .build());

        brandRepository.saveAll(testBrandList);

        postService.insert(PostInsertRequestDTO.builder()
                .title("test1")
                .content("test")
                .price(1)
                .user(userRepository.findAll().get(0))
                .upperCategory(testUpperCategoryList.get(0))
                .lowerCategory(testLowerCategoryList.get(0))
                .brand(testBrandList.get(0))
                .build());

        postService.insert(PostInsertRequestDTO.builder()
                .title("test2")
                .content("test")
                .price(1)
                .user(userRepository.findAll().get(0))
                .upperCategory(testUpperCategoryList.get(0))
                .lowerCategory(testLowerCategoryList.get(0))
                .brand(testBrandList.get(1))
                .build());

        postService.insert(PostInsertRequestDTO.builder()
                .title("test3")
                .content("test")
                .price(1)
                .user(userRepository.findAll().get(0))
                .upperCategory(testUpperCategoryList.get(0))
                .lowerCategory(testLowerCategoryList.get(1))
                .brand(testBrandList.get(2))
                .build());

        postService.insert(PostInsertRequestDTO.builder()
                .title("test4")
                .content("test")
                .price(1)
                .user(userRepository.findAll().get(0))
                .upperCategory(testUpperCategoryList.get(0))
                .lowerCategory(testLowerCategoryList.get(2))
                .brand(testBrandList.get(3))
                .build());

        postService.insert(PostInsertRequestDTO.builder()
                .title("test5")
                .content("test")
                .price(1)
                .user(userRepository.findAll().get(0))
                .upperCategory(testUpperCategoryList.get(0))
                .lowerCategory(testLowerCategoryList.get(2))
                .brand(testBrandList.get(3))
                .build());
    }

    @After
    public void teardown() {
        userRepository.deleteAll();
        postRepository.deleteAll();
        localRepository.deleteAll();
        upperCategoryRepository.deleteAll();
    }

    @Test
    public void selectedAsUpperCategory_검증() {
        SelectFromUpperRequestDTO selectFromUpperRequestDTO =
                SelectFromUpperRequestDTO.builder()
                        .upperCategory(upperCategoryRepository.findAll().get(0))
                        .build();

        List<PostVO> testResultPostList = categorySelectService
                .selectFromUpperCategory(selectFromUpperRequestDTO);

        assertThat(testResultPostList.get(0).getPostNo())
                .isEqualTo(postRepository.findAll().get(0).getPostNo());
    }

    @Test
    public void selectFromLowerCategory_검증() {
        SelectFromLowerRequestDTO selectFromLowerRequestDTO =
                SelectFromLowerRequestDTO.builder()
                        .lowerCategory(lowerCategoryRepository.findAll().get(0))
                        .build();

        List<PostVO> testResultPostList = categorySelectService
                .selectFromLowerCategory(selectFromLowerRequestDTO);

        assertThat(testResultPostList.get(0).getPostNo())
                .isEqualTo(postRepository.findAll().get(0).getPostNo());
        assertThat(testResultPostList.size()).isGreaterThan(1);
    }

    @Test
    public void selectFromBrand_검증() {
        SelectFromBrandRequestDTO selectFromBrandRequestDTO =
                SelectFromBrandRequestDTO.builder()
                        .brand(brandRepository.findAll().get(3))
                        .build();

        List<PostVO> testResultPostList = categorySelectService
                .selectFromBrand(selectFromBrandRequestDTO);

        assertThat(testResultPostList.get(0).getTitle())
                .isEqualTo("test4");
        assertThat(testResultPostList.get(1).getTitle())
                .isEqualTo("test5");
    }
}
