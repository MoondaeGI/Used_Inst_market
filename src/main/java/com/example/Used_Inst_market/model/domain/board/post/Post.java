package com.example.Used_Inst_market.model.domain.board.post;

import com.example.Used_Inst_market.model.domain.board.comment.Comment;
import com.example.Used_Inst_market.model.domain.board.picture.Picture;
import com.example.Used_Inst_market.model.domain.search.Search;
import com.example.Used_Inst_market.model.domain.user.User;
import com.example.Used_Inst_market.model.domain.util.BaseTimeStamp;
import com.example.Used_Inst_market.util.enums.SoldYN;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "TB_POST")
public class Post extends BaseTimeStamp {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_NO")
    private Long postNo;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USER_NO")
    private User user;

    @NotBlank
    @Column(name = "TITLE", nullable = false)
    private String title;

    @NotBlank
    @Column(name = "CONTENT", nullable = false)
    private String content;

    @PositiveOrZero
    @Column(name = "PRICE", nullable = false)
    private Integer price;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "SOLD_YN", nullable = false)
    private SoldYN soldYN;

    @Builder
    public Post(User user, String title, String content, Integer price) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.price = price;
        this.soldYN = SoldYN.SALE;  // default값
    }

    @OneToMany(mappedBy = "post", orphanRemoval = true)
    private List<Picture> pictures = new ArrayList<>();

    @OneToMany(mappedBy = "post", orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToOne(mappedBy = "post", orphanRemoval = true)
    private Search search;

    public void update(String title, String content, Integer price, SoldYN soldYN) {
        this.title = title;
        this.content = content;
        this.price = price;
        this.soldYN = soldYN;
    }

    public void updateSoldYN(SoldYN soldYN) {
        this.soldYN = soldYN;
    }
}
