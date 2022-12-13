package com.example.Used_Inst_market.service.local;

import com.example.Used_Inst_market.model.domain.local.lower.LowerLocal;
import com.example.Used_Inst_market.model.domain.local.lower.LowerLocalRepository;
import com.example.Used_Inst_market.model.domain.local.upper.UpperLocal;
import com.example.Used_Inst_market.model.domain.local.upper.UpperLocalRepository;
import com.example.Used_Inst_market.web.dto.local.lower.LowerLocalDeleteRequestDTO;
import com.example.Used_Inst_market.web.dto.local.lower.LowerLocalInsertRequestDTO;
import com.example.Used_Inst_market.web.dto.local.lower.LowerLocalSelectRequestDTO;
import com.example.Used_Inst_market.web.dto.local.lower.LowerLocalUpdateRequestDTO;
import com.example.Used_Inst_market.web.dto.local.upper.UpperLocalDeleteRequestDTO;
import com.example.Used_Inst_market.web.dto.local.upper.UpperLocalInsertRequestDTO;
import com.example.Used_Inst_market.web.dto.local.upper.UpperLocalSelectRequestDTO;
import com.example.Used_Inst_market.web.dto.local.upper.UpperLocalUpdateRequestDTO;
import com.example.Used_Inst_market.model.vo.local.LowerLocalVO;
import com.example.Used_Inst_market.model.vo.local.UpperLocalVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LocalService {
    private final UpperLocalRepository upperLocalRepository;
    private final LowerLocalRepository lowerLocalRepository;

    @Transactional(readOnly = true)
    public UpperLocalVO upperLocalSelect(
            UpperLocalSelectRequestDTO upperLocalSelectRequestDTO) {
        UpperLocal upperLocal = upperLocalRepository
                .findById(upperLocalSelectRequestDTO.getUpperLocalNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 지역이 없습니다"));

        return UpperLocalVO.from(upperLocal);
    }

    @Transactional(readOnly = true)
    public List<UpperLocalVO> upperLocalSelectAll() {
        return upperLocalRepository.findAll().stream()
                .map(UpperLocalVO::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long upperLocalInsert(
            UpperLocalInsertRequestDTO upperLocalInsertRequestDTO) {
        return upperLocalRepository
                .save(upperLocalInsertRequestDTO.toEntity()).getUpperLocalNo();
    }

    @Transactional
    public Long upperLocalUpdate(
            UpperLocalUpdateRequestDTO upperLocalUpdateRequestDTO) {
        UpperLocal upperLocal = upperLocalRepository
                .findById(upperLocalUpdateRequestDTO.getUpperLocalNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 지역이 없습니다"));

        upperLocal.update(upperLocalUpdateRequestDTO.getName());

        return upperLocalUpdateRequestDTO.getUpperLocalNo();
    }

    @Transactional
    public void upperLocalDelete(
            UpperLocalDeleteRequestDTO upperLocalDeleteRequestDTO) {
        UpperLocal upperLocal = upperLocalRepository
                .findById(upperLocalDeleteRequestDTO.getUpperLocalNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 지역이 없습니다"));

        upperLocalRepository.delete(upperLocal);
    }

    @Transactional(readOnly = true)
    public LowerLocalVO lowerLocalSelect(
            LowerLocalSelectRequestDTO lowerLocalSelectRequestDTO) {
        LowerLocal lowerLocal = lowerLocalRepository
                .findById(lowerLocalSelectRequestDTO.getLowerLocalNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 지역이 없습니다"));

        return new LowerLocalVO(lowerLocal);
    }

    @Transactional(readOnly = true)
    public List<LowerLocalVO> lowerLocalSelectAll() {
        return lowerLocalRepository.findAll().stream()
                .map(LowerLocalVO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long lowerLocalInsert(
            LowerLocalInsertRequestDTO lowerLocalInsertRequestDTO) {
        return lowerLocalRepository
                .save(lowerLocalInsertRequestDTO.toEntity()).getLowerLocalNo();
    }

    @Transactional
    public Long lowerLocalUpdate(LowerLocalUpdateRequestDTO lowerLocalUpdateRequestDTO) {
        LowerLocal lowerLocal = lowerLocalRepository
                .findById(lowerLocalUpdateRequestDTO.getLowerLocalNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 지역이 없습니다"));

        lowerLocal.update(lowerLocalUpdateRequestDTO.getName(),
                lowerLocalUpdateRequestDTO.getUpperLocal());

        return lowerLocalUpdateRequestDTO.getLowerLocalNo();
    }

    @Transactional
    public void lowerLocalDelete(LowerLocalDeleteRequestDTO lowerLocalDeleteRequestDTO) {
        LowerLocal lowerLocal = lowerLocalRepository
                .findById(lowerLocalDeleteRequestDTO.getLowerLocalNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 지역이 없습니다"));

        lowerLocalRepository.delete(lowerLocal);
    }
}
