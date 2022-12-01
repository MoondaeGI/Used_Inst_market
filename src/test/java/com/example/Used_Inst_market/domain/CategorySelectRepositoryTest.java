package com.example.Used_Inst_market.domain;

import com.example.Used_Inst_market.domain.address.addressdetail.Address;
import com.example.Used_Inst_market.domain.address.addressdetail.AddressRepository;
import com.example.Used_Inst_market.domain.address.city.City;
import com.example.Used_Inst_market.domain.address.city.CityRepository;
import com.example.Used_Inst_market.domain.address.local.Local;
import com.example.Used_Inst_market.domain.address.local.LocalRepository;
import com.example.Used_Inst_market.domain.category.brand.Brand;
import com.example.Used_Inst_market.domain.category.brand.BrandRepository;
import com.example.Used_Inst_market.domain.select.categoryselect.CategorySelect;
import com.example.Used_Inst_market.domain.select.categoryselect.CategorySelectRepository;
import com.example.Used_Inst_market.domain.category.lower.LowerCategory;
import com.example.Used_Inst_market.domain.category.lower.LowerCategoryRepository;
import com.example.Used_Inst_market.domain.category.upper.UpperCategory;
import com.example.Used_Inst_market.domain.category.upper.UpperCategoryRepository;
import com.example.Used_Inst_market.domain.post.Post;
import com.example.Used_Inst_market.domain.post.PostRepository;
import com.example.Used_Inst_market.domain.user.User;
import com.example.Used_Inst_market.domain.user.UserRepository;
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
public class CategorySelectRepositoryTest {
    @Autowired
    private CategorySelectRepository categorySelectRepository;

    @Autowired private PostRepository postRepository;
    @Autowired private UpperCategoryRepository upperCategoryRepository;
    @Autowired private LowerCategoryRepository lowerCategoryRepository;
    @Autowired private BrandRepository brandRepository;

    @Autowired private UserRepository userRepository;
    @Autowired private LocalRepository localRepository;
    @Autowired private CityRepository cityRepository;
    @Autowired private AddressRepository addressRepository;

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
    }

    @After
    public void teardown() {
        cityRepository.deleteAll();
        localRepository.deleteAll();

        brandRepository.deleteAll();
        lowerCategoryRepository.deleteAll();
        upperCategoryRepository.deleteAll();
    }

    @Test
    public void TB_PD_CT_SELECT_삽입검증() {
        Long testPostNo = postRepository.save(
                Post.builder()
                        .title("test")
                        .content("test")
                        .price(1)
                        .user(userRepository.findAll().get(0))
                        .build()).getPostNo();

        CategorySelect testCategorySelect = categorySelectRepository.save(
                CategorySelect.builder()
                        .post(postRepository.findAll().get(0))
                        .upperCategory(upperCategoryRepository.findAll().get(0))
                        .lowerCategory(lowerCategoryRepository.findAll().get(0))
                        .brand(brandRepository.findAll().get(0))
                        .build());

        assertThat(categorySelectRepository.findAll().get(0).getCategorySelectNo())
                .isEqualTo(testCategorySelect.getCategorySelectNo());
        assertThat(categorySelectRepository.findAll().get(0).getPost())
                .isEqualTo(testPostNo);
    }
}
