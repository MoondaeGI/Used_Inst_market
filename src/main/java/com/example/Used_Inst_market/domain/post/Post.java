package com.example.Used_Inst_market.domain.post;

import com.example.Used_Inst_market.domain.file.File;
import com.example.Used_Inst_market.domain.select.categoryselect.CategorySelect;
import com.example.Used_Inst_market.domain.select.localselect.LocalSelect;
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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USER_NO")
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

    @OneToMany(mappedBy = "post", orphanRemoval = true)
    private List<File> fileList = new ArrayList<>();

    @OneToOne(mappedBy = "post", orphanRemoval = true)
    private CategorySelect categorySelect;

    @OneToOne(mappedBy = "post", orphanRemoval = true)
    private LocalSelect localSelect;

    public void update(String title, String content, Integer price, SoldYN soldYN) {
        this.title = title;
        this.content = content;
        this.price = price;
        this.soldYN = soldYN;
    }
}
