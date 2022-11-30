package com.example.Used_Inst_market.domain.address.addressdetail;

import com.example.Used_Inst_market.domain.address.city.City;
import com.example.Used_Inst_market.domain.address.local.Local;
import com.example.Used_Inst_market.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "TB_ADDRESS")
public class Address {
    @Id
    @Column(name = "ADDRESS_NO")
    private Long addressNo;

    @MapsId
    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "USER_NO")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOCAL_NO", nullable = false)
    private Local local;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CITY_NO", nullable = false)
    private City city;

    @Column(name = "ADDRESS_DETAIL", nullable = false)
    private String addressDetail;

    @Builder
    public Address(Local local, City city, String addressDetail) {
        this.local = local;
        this.city = city;
        this.addressDetail = addressDetail;
    }
}
