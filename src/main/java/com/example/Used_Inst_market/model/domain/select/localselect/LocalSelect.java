package com.example.Used_Inst_market.model.domain.select.localselect;

import com.example.Used_Inst_market.model.domain.local.lower.LowerLocal;
import com.example.Used_Inst_market.model.domain.board.post.Post;
import com.example.Used_Inst_market.model.domain.local.upper.UpperLocal;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "TB_LOCAL_SELECT")
public class LocalSelect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOCAL_SELECT_NO")
    private Long localSelectNo;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "POST_NO")
    private Post post;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "LOCAL_NO")
    private UpperLocal upperLocal;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CITY_NO")
    private LowerLocal lowerLocal;

    @Builder
    public LocalSelect(Post post, UpperLocal upperLocal, LowerLocal lowerLocal) {
        this.post = post;
        this.upperLocal = upperLocal;
        this.lowerLocal = lowerLocal;
    }

    public void update(UpperLocal upperLocal, LowerLocal lowerLocal) {
        this.upperLocal = upperLocal;
        this.lowerLocal = lowerLocal;
    }
}
