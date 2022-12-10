package com.example.Used_Inst_market.model.domain.address;

import com.example.Used_Inst_market.model.domain.city.City;
import com.example.Used_Inst_market.model.domain.local.Local;
import com.example.Used_Inst_market.model.domain.user.User;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDRESS_NO")
    private Long addressNo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_NO", nullable = false)
    public User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOCAL_NO", nullable = false)
    private Local local;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CITY_NO", nullable = false)
    private City city;

    @Column(name = "ADDRESS_DETAIL", nullable = false)
    private String addressDetail;

    @Builder
    public Address(User user, Local local, City city, String addressDetail) {
        this.user = user;
        this.local = local;
        this.city = city;
        this.addressDetail = addressDetail;
    }
}
