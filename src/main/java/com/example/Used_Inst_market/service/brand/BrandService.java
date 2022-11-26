package com.example.Used_Inst_market.service.brand;

import com.example.Used_Inst_market.domain.brand.Brand;
import com.example.Used_Inst_market.domain.brand.BrandRepository;
import com.example.Used_Inst_market.web.dto.brand.BrandDeleteRequestDTO;
import com.example.Used_Inst_market.web.dto.brand.BrandInsertRequestDTO;
import com.example.Used_Inst_market.web.dto.brand.BrandSelectRequestDTO;
import com.example.Used_Inst_market.web.dto.brand.BrandUpdateRequestDTO;
import com.example.Used_Inst_market.web.vo.brand.BrandInfoVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BrandService {
    private final BrandRepository brandRepository;

    @Transactional
    public BrandInfoVO select(BrandSelectRequestDTO brandSelectRequestDTO) {
        Brand brand = brandRepository.findById(brandSelectRequestDTO.getBrandNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 브랜드가 없습니다."));

        return new BrandInfoVO(brand);
    }

    @Transactional
    public List<BrandInfoVO> selectAll() {
        return brandRepository.findAll().stream()
                .map(BrandInfoVO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long insert(BrandInsertRequestDTO brandInsertRequestDTO) {
        return brandRepository.save(brandInsertRequestDTO.toEntity()).getBrandNo();
    }

    @Transactional
    public Long update(BrandUpdateRequestDTO brandUpdateRequestDTO) {
        Brand brand = brandRepository.findById(brandUpdateRequestDTO.getBrandNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 브랜드가 없습니다."));

        brand.update(brandUpdateRequestDTO.getLowerCategory(), brandUpdateRequestDTO.getName());

        return brand.getBrandNo();
    }

    @Transactional
    public void delete(BrandDeleteRequestDTO brandDeleteRequestDTO) {
        Brand brand = brandRepository.findById(brandDeleteRequestDTO.getBrandNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 브렌드가 없습니다."));

        brandRepository.delete(brand);
    }
}
