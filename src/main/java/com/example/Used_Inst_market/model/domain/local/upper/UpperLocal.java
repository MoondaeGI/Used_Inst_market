package com.example.Used_Inst_market.model.domain.local.upper;

import com.example.Used_Inst_market.model.domain.local.lower.LowerLocal;
import com.example.Used_Inst_market.model.domain.util.BaseTimeStamp;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "TB_UPPER_LO")
public class UpperLocal extends BaseTimeStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UPPER_LO_NO")
    private Long upperLocalNo;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Builder
    public UpperLocal(String name) { this.name = name; }

    @OneToMany(mappedBy = "upperLocal", orphanRemoval = true)
    private List<LowerLocal> lowerLocals = new ArrayList<LowerLocal>();

    public void update(String name) { this.name = name; }
}
