package com.example.Used_Inst_market.model.domain.local.lower;

import com.example.Used_Inst_market.model.domain.local.upper.UpperLocal;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "TB_LOWER_LO")
public class LowerLocal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOWER_LO_NO")
    private Long lowerLocalNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UPPER_LO_NO", nullable = false)
    private UpperLocal upperLocal;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Builder
    public LowerLocal(UpperLocal upperLocal, String name) {
        this.upperLocal = upperLocal;
        this.name = name;
    }

    public void update(String name, UpperLocal UpperLocal) {
        this.name = name;
        this.upperLocal = upperLocal;
    }
}
