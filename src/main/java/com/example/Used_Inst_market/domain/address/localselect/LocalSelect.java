package com.example.Used_Inst_market.domain.address.localselect;

import com.example.Used_Inst_market.domain.address.city.City;
import com.example.Used_Inst_market.domain.address.local.Local;
import com.example.Used_Inst_market.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "TB_LOCAL_SELECT")
public class LocalSelect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOCAL_SELECT_NO")
    private Long localSelectNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOCAL_NO", nullable = false)
    private Local local;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CITY_NO", nullable = false)
    private City city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_NO", nullable = false)
    private Post post;

    @Builder
    public LocalSelect(Local local, City city, Post post) {
        this.local = local;
        this.city = city;
        this.post = post;
    }
}
