package com.example.Used_Inst_market.domain.transactionhistory;

import com.example.Used_Inst_market.domain.product.Product;
import com.example.Used_Inst_market.domain.user.User;
import com.example.Used_Inst_market.domain.util.BaseTimeStamp;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "TB_TS_HISTORY")
public class TransactionHistory extends BaseTimeStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TS_HISTORY_NO")
    private Long tsHistoryNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_NO", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_NO", nullable = false)
    private Product product;

    @Builder
    public TransactionHistory(User user, Product product) {
        this.user = user;
        this.product = product;
    }
}
