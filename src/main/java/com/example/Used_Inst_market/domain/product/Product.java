package com.example.Used_Inst_market.domain.product;

import com.example.Used_Inst_market.domain.brand.Brand;
import com.example.Used_Inst_market.domain.post.Post;
import com.example.Used_Inst_market.domain.select.category.CategorySelect;
import com.example.Used_Inst_market.domain.transactionhistory.TransactionHistory;
import com.example.Used_Inst_market.domain.util.BaseTimeStamp;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "TB_PRODUCT")
public class Product extends BaseTimeStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_NO")
    private Long productNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_NO", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BRAND_NO", nullable = false)
    private Brand brand;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "PRICE", nullable = false)
    private Integer price;

    @Builder
    public Product(Post post, Brand brand, String name, Integer price) {
        this.post = post;
        this.brand = brand;
        this.name = name;
        this.price = price;
    }

    @OneToMany(mappedBy = "product")
    private List<CategorySelect> categorySelects = new ArrayList<CategorySelect>();

    @OneToMany(mappedBy = "product")
    private List<TransactionHistory> transactionHistories = new ArrayList<TransactionHistory>();

    public void update(Brand brand, String name, Integer price) {
        this.brand = brand;
        this.name = name;
        this.price = price;
    }
}
