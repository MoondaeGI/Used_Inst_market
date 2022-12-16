package com.example.Used_Inst_market.service.local;

import com.example.Used_Inst_market.model.domain.local.lower.LowerLocal;
import com.example.Used_Inst_market.model.domain.local.lower.LowerLocalRepository;
import com.example.Used_Inst_market.model.domain.local.upper.UpperLocal;
import com.example.Used_Inst_market.model.domain.local.upper.UpperLocalRepository;
import com.example.Used_Inst_market.web.dto.local.lower.LowerLocalDeleteDTO;
import com.example.Used_Inst_market.web.dto.local.lower.LowerLocalInsertDTO;
import com.example.Used_Inst_market.web.dto.local.lower.LowerLocalSelectDTO;
import com.example.Used_Inst_market.web.dto.local.lower.LowerLocalUpdateDTO;
import com.example.Used_Inst_market.web.dto.local.upper.UpperLocalDeleteDTO;
import com.example.Used_Inst_market.web.dto.local.upper.UpperLocalInsertDTO;
import com.example.Used_Inst_market.web.dto.local.upper.UpperLocalSelectDTO;
import com.example.Used_Inst_market.web.dto.local.upper.UpperLocalUpdateDTO;
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
            UpperLocalSelectDTO upperLocalSelectDTO) {
        UpperLocal upperLocal = upperLocalRepository
                .findById(upperLocalSelectDTO.getUpperLocalNo())
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
            UpperLocalInsertDTO upperLocalInsertDTO) {
        return upperLocalRepository
                .save(upperLocalInsertDTO.toEntity()).getUpperLocalNo();
    }

    @Transactional
    public Long upperLocalUpdate(
            UpperLocalUpdateDTO upperLocalUpdateDTO) {
        UpperLocal upperLocal = upperLocalRepository
                .findById(upperLocalUpdateDTO.getUpperLocalNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 지역이 없습니다"));

        upperLocal.update(upperLocalUpdateDTO.getName());

        return upperLocalUpdateDTO.getUpperLocalNo();
    }

    @Transactional
    public void upperLocalDelete(
            UpperLocalDeleteDTO upperLocalDeleteDTO) {
        UpperLocal upperLocal = upperLocalRepository
                .findById(upperLocalDeleteDTO.getUpperLocalNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 지역이 없습니다"));

        upperLocalRepository.delete(upperLocal);
    }

    @Transactional(readOnly = true)
    public LowerLocalVO lowerLocalSelect(
            LowerLocalSelectDTO lowerLocalSelectDTO) {
        LowerLocal lowerLocal = lowerLocalRepository
                .findById(lowerLocalSelectDTO.getLowerLocalNo())
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
            LowerLocalInsertDTO lowerLocalInsertDTO) {
        return lowerLocalRepository
                .save(lowerLocalInsertDTO.toEntity()).getLowerLocalNo();
    }

    @Transactional
    public Long lowerLocalUpdate(LowerLocalUpdateDTO lowerLocalUpdateDTO) {
        LowerLocal lowerLocal = lowerLocalRepository
                .findById(lowerLocalUpdateDTO.getLowerLocalNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 지역이 없습니다"));

        lowerLocal.update(lowerLocalUpdateDTO.getName(),
                lowerLocalUpdateDTO.getUpperLocal());

        return lowerLocalUpdateDTO.getLowerLocalNo();
    }

    @Transactional
    public void lowerLocalDelete(LowerLocalDeleteDTO lowerLocalDeleteDTO) {
        LowerLocal lowerLocal = lowerLocalRepository
                .findById(lowerLocalDeleteDTO.getLowerLocalNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 지역이 없습니다"));

        lowerLocalRepository.delete(lowerLocal);
    }
}
