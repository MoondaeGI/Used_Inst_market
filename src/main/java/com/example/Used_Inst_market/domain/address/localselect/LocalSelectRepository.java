package com.example.Used_Inst_market.domain.address.localselect;

import com.example.Used_Inst_market.domain.address.city.City;
import com.example.Used_Inst_market.domain.address.local.Local;
import com.example.Used_Inst_market.web.vo.post.PostVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LocalSelectRepository extends JpaRepository<LocalSelect, Long> {
    @Query("SELECT localSelect.post " +
            "FROM LocalSelect localSelect " +
            "WHERE localSelect.local = :local")
    List<PostVO> findByLocal(@Param("local") Local local);

    @Query("SELECT localSelect.post " +
            "FROM LocalSelect localSelect " +
            "WHERE localSelect.city = :city")
    List<PostVO> findByCity(@Param("city") City city);
}