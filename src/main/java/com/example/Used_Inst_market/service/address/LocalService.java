package com.example.Used_Inst_market.service.address;

import com.example.Used_Inst_market.domain.address.local.Local;
import com.example.Used_Inst_market.domain.address.local.LocalRepository;
import com.example.Used_Inst_market.web.dto.address.local.*;
import com.example.Used_Inst_market.web.vo.address.LocalInfoVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LocalService {
    private final LocalRepository localRepository;

    @Transactional
    public LocalInfoVO select(LocalSelectRequestDTO localSelectRequestDTO) {
        Local local = localRepository.findById(localSelectRequestDTO.getLocalNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 지역이 없습니다"));

        return new LocalInfoVO(local);
    }

    @Transactional
    public List<LocalInfoVO> selectAll() {
        return localRepository.findAll().stream()
                .map(LocalInfoVO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long insert(LocalInsertRequestDTO localInsertRequestDTO) {
        return localRepository.save(localInsertRequestDTO.toEntity()).getLocalNo();
    }

    @Transactional
    public Long update(LocalUpdateRequestDTO localUpdateRequestDTO) {
        Local local = localRepository.findById(localUpdateRequestDTO.getLocalNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 지역이 없습니다"));

        local.update(localUpdateRequestDTO.getName());

        return localUpdateRequestDTO.getLocalNo();
    }

    @Transactional
    public void delete(LocalDeleteRequestDTO localDeleteRequestDTO) {
        Local local = localRepository.findById(localDeleteRequestDTO.getLocalNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 지역이 없습니다"));

        localRepository.delete(local);
    }
}
