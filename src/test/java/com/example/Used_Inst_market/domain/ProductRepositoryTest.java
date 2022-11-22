package com.example.Used_Inst_market.domain;

import com.example.Used_Inst_market.domain.product.Product;
import com.example.Used_Inst_market.domain.product.ProductRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @After
    public void teardown() {
        productRepository.deleteAll();
    }

    @Test
    public void TB_PRODUCT_추가() {
        String testName = "test product";
        Integer testPrice = 100000;

        Product product = Product.builder()
                .name(testName)
                .price(testPrice)
                .build();

        productRepository.save(product);
        List<Product> products = productRepository.findAll();

        assertThat(products.get(0).getName()).isEqualTo(testName);
        assertThat(products.get(0).getPrice()).isEqualTo(testPrice);
    }
}
