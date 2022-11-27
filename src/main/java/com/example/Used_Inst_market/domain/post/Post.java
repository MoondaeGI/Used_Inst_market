package com.example.Used_Inst_market.domain.post;

import com.example.Used_Inst_market.domain.product.Product;
import com.example.Used_Inst_market.domain.select.local.LocalSelect;
import com.example.Used_Inst_market.domain.user.User;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "SOLD_YN", nullable = false)
    private SoldYN soldYN;

    @Builder
    public Post(User user, String title, String content) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.soldYN = SoldYN.SALE;  // default값
    }

    @Getter(AccessLevel.NONE)
    @OneToMany(mappedBy = "post")
    private List<Product> products = new ArrayList<Product>();

    @Getter(AccessLevel.NONE)
    @OneToMany(mappedBy = "post")
    private List<LocalSelect> localSelects = new ArrayList<LocalSelect>();

    public void update(String title, String content, SoldYN soldYN) {
        this.title = title;
        this.content = content;
        this.soldYN = soldYN;
    }
}
