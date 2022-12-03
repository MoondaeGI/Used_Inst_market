package com.example.Used_Inst_market.service.address;

import com.example.Used_Inst_market.domain.address.city.City;
import com.example.Used_Inst_market.domain.address.city.CityRepository;
import com.example.Used_Inst_market.domain.address.local.Local;
import com.example.Used_Inst_market.domain.address.local.LocalRepository;
import com.example.Used_Inst_market.web.dto.address.city.CityDeleteRequestDTO;
import com.example.Used_Inst_market.web.dto.address.city.CityInsertRequestDTO;
import com.example.Used_Inst_market.web.dto.address.city.CitySelectRequestDTO;
import com.example.Used_Inst_market.web.dto.address.city.CityUpdateRequestDTO;
import com.example.Used_Inst_market.web.dto.address.local.*;
import com.example.Used_Inst_market.web.vo.address.CityVO;
import com.example.Used_Inst_market.web.vo.address.LocalVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LocalService {
    private final LocalRepository localRepository;
    private final CityRepository cityRepository;

    @Transactional(readOnly = true)
    public LocalVO localSelect(LocalSelectRequestDTO localSelectRequestDTO) {
        Local local = localRepository.findById(localSelectRequestDTO.getLocalNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 지역이 없습니다"));

        return LocalVO.from(local);
    }

    @Transactional(readOnly = true)
    public List<LocalVO> localSelectAll() {
        return localRepository.findAll().stream()
                .map(LocalVO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long localInsert(LocalInsertRequestDTO localInsertRequestDTO) {
        return localRepository.save(localInsertRequestDTO.toEntity()).getLocalNo();
    }

    @Transactional
    public Long localUpdate(LocalUpdateRequestDTO localUpdateRequestDTO)
            throws IllegalArgumentException {
        Local local = localRepository.findById(localUpdateRequestDTO.getLocalNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 지역이 없습니다"));

        local.update(localUpdateRequestDTO.getName());

        return localUpdateRequestDTO.getLocalNo();
    }

    @Transactional
    public void localDelete(LocalDeleteRequestDTO localDeleteRequestDTO)
            throws IllegalArgumentException {
        Local local = localRepository.findById(localDeleteRequestDTO.getLocalNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 지역이 없습니다"));

        localRepository.delete(local);
    }

    @Transactional(readOnly = true)
    public CityVO citySelect(CitySelectRequestDTO citySelectRequestDTO) {
        City city = cityRepository.findById(citySelectRequestDTO.getCityNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 도시가 없습니다."));

        return new CityVO(city);
    }

    @Transactional(readOnly = true)
    public List<CityVO> citySelectAll() {
        return cityRepository.findAll().stream()
                .map(CityVO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long cityInsert(CityInsertRequestDTO cityInsertRequestDTO) {
        return cityRepository.save(cityInsertRequestDTO.toEntity()).getCityNo();
    }

    @Transactional
    public Long cityUpdate(CityUpdateRequestDTO cityUpdateRequestDTO) {
        City city = cityRepository.findById(cityUpdateRequestDTO.getCityNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 도시가 없습니다."));

        city.update(cityUpdateRequestDTO.getName(), cityUpdateRequestDTO.getLocal());

        return cityUpdateRequestDTO.getCityNo();
    }

    @Transactional
    public void cityDelete(CityDeleteRequestDTO cityDeleteRequestDTO) {
        City city = cityRepository.findById(cityDeleteRequestDTO.getCityNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 도시가 없습니다."));

        cityRepository.delete(city);
    }
}
