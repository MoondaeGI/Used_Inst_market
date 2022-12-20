package com.example.Used_Inst_market.model.domain.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeStamp {
    @PastOrPresent
    @CreationTimestamp
    @Column(name = "REG_DT", nullable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP() NOT NULL")
    private LocalDateTime regDt;

    @PastOrPresent
    @UpdateTimestamp
    @Column(name = "MOD_DT", nullable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP() NOT NULL")
    private LocalDateTime modDt;
}
