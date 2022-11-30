package com.example.Used_Inst_market.domain.address.local;

import com.example.Used_Inst_market.domain.address.city.City;
import com.example.Used_Inst_market.domain.util.BaseTimeStamp;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "TB_LOCAL")
public class Local extends BaseTimeStamp{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOCAL_NO")
    private Long localNo;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Builder
    public Local(String name) { this.name = name; }

    @Getter(AccessLevel.NONE)
    @OneToMany(mappedBy = "local", orphanRemoval = true)
    private List<City> cities = new ArrayList<City>();

    public void update(String name) { this.name = name; }
}
