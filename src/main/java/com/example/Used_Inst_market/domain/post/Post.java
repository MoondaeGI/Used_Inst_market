package com.example.Used_Inst_market.domain.post;

import com.example.Used_Inst_market.domain.category.categoryselect.CategorySelect;
import com.example.Used_Inst_market.domain.address.localselect.LocalSelect;
import com.example.Used_Inst_market.domain.user.User;
import com.example.Used_Inst_market.domain.util.BaseTimeStamp;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "TB_POST")
public class Post extends BaseTimeStamp {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_NO")
    private Long postNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_NO", nullable = false)
    private User user;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "CONTENT", nullable = false)
    private String content;

    @Column(name = "PRICE", nullable = false)
    private Integer price;

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

    @Getter(AccessLevel.NONE)
    @OneToOne(mappedBy = "post", cascade = CascadeType.ALL)
    private CategorySelect categorySelect;

    @Getter(AccessLevel.NONE)
    @OneToOne(mappedBy = "post", cascade = CascadeType.ALL)
    private LocalSelect localSelect;

    public void update(String title, String content, Integer price, SoldYN soldYN) {
        this.title = title;
        this.content = content;
        this.price = price;
        this.soldYN = soldYN;
    }
}
