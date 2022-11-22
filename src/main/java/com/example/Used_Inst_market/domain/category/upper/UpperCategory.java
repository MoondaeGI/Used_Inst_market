package com.example.Used_Inst_market.domain.category.upper;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "TB_PD_UPPER_CT")
public class UpperCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PD_UPPER_CT_NO")
    private Long upperCategoryNo;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Builder
    public UpperCategory(String name) {
        this.name = name;
    }
}
