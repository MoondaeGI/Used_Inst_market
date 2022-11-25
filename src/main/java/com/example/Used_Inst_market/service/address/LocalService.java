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
    public LocalInfoVO select(SelectLocalRequestDTO selectLocalRequestDTO) {
        Local local = localRepository.findById(selectLocalRequestDTO.getLocalNo())
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
    public Long insert(InsertLocalRequestDTO insertLocalRequestDTO) {
        return localRepository.save(insertLocalRequestDTO.toEntity()).getLocalNo();
    }

    @Transactional
    public Long update(UpdateLocalRequestDTO updateLocalRequestDTO) {
        Local local = localRepository.findById(updateLocalRequestDTO.getLocalNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 지역이 없습니다"));

        local.update(updateLocalRequestDTO.getName());

        return updateLocalRequestDTO.getLocalNo();
    }

    @Transactional
    public void delete(DeleteLocalRequestDTO deleteLocalRequestDTO) {
        Local local = localRepository.findById(deleteLocalRequestDTO.getLocalNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 지역이 없습니다"));

        localRepository.delete(local);
    }
}
