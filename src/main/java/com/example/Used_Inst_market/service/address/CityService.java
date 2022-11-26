package com.example.Used_Inst_market.service.address;

import com.example.Used_Inst_market.domain.address.city.City;
import com.example.Used_Inst_market.domain.address.city.CityRepository;
import com.example.Used_Inst_market.web.dto.address.city.CityDeleteRequestDTO;
import com.example.Used_Inst_market.web.dto.address.city.CityInsertRequestDTO;
import com.example.Used_Inst_market.web.dto.address.city.CitySelectRequestDTO;
import com.example.Used_Inst_market.web.dto.address.city.CityUpdateRequestDTO;
import com.example.Used_Inst_market.web.vo.address.CityInfoVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CityService {
    private final CityRepository cityRepository;

    @Transactional
    public CityInfoVO select(CitySelectRequestDTO citySelectRequestDTO) {
        City city = cityRepository.findById(citySelectRequestDTO.getCityNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 도시가 없습니다."));

        return new CityInfoVO(city);
    }

    @Transactional
    public List<CityInfoVO> selectAll() {
        return cityRepository.findAll().stream()
                .map(CityInfoVO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long insert(CityInsertRequestDTO cityInsertRequestDTO) {
        return cityRepository.save(cityInsertRequestDTO.toEntity()).getCityNo();
    }

    @Transactional
    public Long update(CityUpdateRequestDTO cityUpdateRequestDTO) {
        City city = cityRepository.findById(cityUpdateRequestDTO.getCityNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 도시가 없습니다."));

        city.update(cityUpdateRequestDTO.getName(), cityUpdateRequestDTO.getLocal());

        return cityUpdateRequestDTO.getCityNo();
    }

    @Transactional
    public void delete(CityDeleteRequestDTO cityDeleteRequestDTO) {
        City city = cityRepository.findById(cityDeleteRequestDTO.getCityNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 도시가 없습니다."));

        cityRepository.delete(city);
    }
}
