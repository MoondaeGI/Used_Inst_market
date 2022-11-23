package com.example.Used_Inst_market.domain.util;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public class BaseTimeStamp {
    @CreationTimestamp
    @Column(name = "REG_DT", nullable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP() NOT NULL")
    private LocalDateTime regDt;

    @UpdateTimestamp
    @Column(name = "MOD_DT", nullable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP() NOT NULL")
    private LocalDateTime modDt;
}
