package com.example.Used_Inst_market.domain.post;

import com.example.Used_Inst_market.domain.BaseTimeStamp;
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

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "CONTENT", nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "SOLD_YN", nullable = false)
    private SoldYN soldYN;

    @Builder
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
        this.soldYN = SoldYN.SALE;  // default값
    }
}
