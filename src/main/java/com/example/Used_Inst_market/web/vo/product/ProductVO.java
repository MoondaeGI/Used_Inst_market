package com.example.Used_Inst_market.web.vo.product;

import com.example.Used_Inst_market.domain.brand.Brand;
import com.example.Used_Inst_market.domain.post.Post;
import com.example.Used_Inst_market.domain.product.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductVO {
    private Long productNo;
    private Post post;
    private Brand brand;
    private String name;
    private Integer price;

    @Builder
    public ProductVO(Product product) {
        this.productNo = product.getProductNo();
        this.post = product.getPost();
        this.brand = product.getBrand();
        this.name = product.getName();
        this.price = product.getPrice();
    }
}
