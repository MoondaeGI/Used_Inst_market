package com.example.Used_Inst_market.model.domain.local.lower;

import com.example.Used_Inst_market.model.domain.local.upper.UpperLocal;
import com.example.Used_Inst_market.model.domain.search.Search;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "TB_LOWER_LO")
public class LowerLocal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOWER_LO_NO")
    private Long lowerLocalNo;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UPPER_LO_NO", nullable = false)
    private UpperLocal upperLocal;

    @NotBlank
    @Column(name = "NAME", nullable = false)
    private String name;

    @Builder
    public LowerLocal(UpperLocal upperLocal, String name) {
        this.upperLocal = upperLocal;
        this.name = name;
    }

    @OneToMany(mappedBy = "lowerLocal", orphanRemoval = true)
    private List<Search> searches = new ArrayList<>();

    public void update(String name, UpperLocal UpperLocal) {
        this.name = name;
        this.upperLocal = upperLocal;
    }
}
