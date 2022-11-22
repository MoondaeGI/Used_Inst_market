package com.example.Used_Inst_market.domain.product;

import com.example.Used_Inst_market.domain.BaseTimeStamp;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "TB_PRODUCT")
public class Product extends BaseTimeStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_NO")
    private Long productNo;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "PRICE", nullable = false)
    private Integer price;

    @Builder
    public Product(String name, Integer price) {
        this.name = name;
        this.price = price;
    }
}
