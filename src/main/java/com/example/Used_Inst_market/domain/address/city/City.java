package com.example.Used_Inst_market.domain.address.city;

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

    @Column(name = "NAME", nullable = false)
    private String name;

    @Builder
    public City(String name) {
        this.name = name;
    }
}
