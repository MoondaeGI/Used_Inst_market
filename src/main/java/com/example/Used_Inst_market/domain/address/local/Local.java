package com.example.Used_Inst_market.domain.address.local;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "TB_LOCAL")
public class Local {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOCAL_NO")
    private Long localNo;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Builder
    public Local(String name) {
        this.name = name;
    }
}
