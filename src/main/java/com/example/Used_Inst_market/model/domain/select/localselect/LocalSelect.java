package com.example.Used_Inst_market.model.domain.select.localselect;

import com.example.Used_Inst_market.model.domain.city.City;
import com.example.Used_Inst_market.model.domain.local.Local;
import com.example.Used_Inst_market.model.domain.board.post.Post;
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

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "POST_NO")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "LOCAL_NO")
    private Local local;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CITY_NO")
    private City city;

    @Builder
    public LocalSelect(Post post, Local local, City city) {
        this.post = post;
        this.local = local;
        this.city = city;
    }
}
