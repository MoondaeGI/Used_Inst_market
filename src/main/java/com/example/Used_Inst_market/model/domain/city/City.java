package com.example.Used_Inst_market.model.domain.city;

import com.example.Used_Inst_market.model.domain.local.Local;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "TB_CITY")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CITY_NO")
    private Long cityNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOCAL_NO", nullable = false)
    private Local local;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Builder
    public City(Local local, String name) {
        this.local = local;
        this.name = name;
    }

    public void update(String name, Local local) {
        this.name = name;
        this.local = local;
    }
}
