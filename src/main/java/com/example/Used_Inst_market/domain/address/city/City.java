package com.example.Used_Inst_market.domain.address.city;

import com.example.Used_Inst_market.domain.address.addressdetail.Address;
import com.example.Used_Inst_market.domain.address.local.Local;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @JoinColumn(name = "LOCAL_NO")
    private Local local;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Builder
    public City(Local local, String name) {
        this.local = local;
        this.name = name;
    }

    @OneToMany(mappedBy = "city")
    private List<Address> addresses = new ArrayList<Address>();
}
