package com.example.Used_Inst_market.domain.category.lower;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "TB_PD_LOWER_CT")
public class LowerCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PD_LOWER_CT_NO")
    private Long lowerCategoryNo;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Builder
    public LowerCategory(String name) {
        this.name = name;
    }
}
