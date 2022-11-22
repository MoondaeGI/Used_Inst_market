package com.example.Used_Inst_market.domain.brand;

import com.example.Used_Inst_market.domain.BaseTimeStamp;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "TB_BRAND")
public class Brand extends BaseTimeStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BRAND_NO")
    private Long brandNo;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Builder
    public Brand(String name) {
        this.name = name;
    }
}
