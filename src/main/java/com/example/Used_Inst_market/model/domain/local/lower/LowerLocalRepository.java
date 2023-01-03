package com.example.Used_Inst_market.model.domain.local.lower;

import com.example.Used_Inst_market.model.domain.local.upper.UpperLocal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LowerLocalRepository extends JpaRepository<LowerLocal, Long> {
    List<LowerLocal> findByUpperLocal(UpperLocal upperLocal);
}
