package com.example.Used_Inst_market.web.dto.product;

import com.example.Used_Inst_market.domain.brand.Brand;
import com.example.Used_Inst_market.domain.post.Post;
import com.example.Used_Inst_market.domain.product.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductInsertRequestDTO {
    private Post post;
    private Brand brand;
    private String name;
    private Integer price;

    @Builder
    public ProductInsertRequestDTO(Post post, Brand brand, String name, Integer price) {
        this.post = post;
        this.brand = brand;
        this.name = name;
        this.price = price;
    }

    public Product toEntity() {
        return Product.builder()
                .post(post)
                .brand(brand)
                .name(name)
                .price(price)
                .build();
    }
}
