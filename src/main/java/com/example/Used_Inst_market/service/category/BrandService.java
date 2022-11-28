package com.example.Used_Inst_market.service.category;

import com.example.Used_Inst_market.domain.category.brand.Brand;
import com.example.Used_Inst_market.domain.category.brand.BrandRepository;
import com.example.Used_Inst_market.web.dto.category.brand.BrandDeleteRequestDTO;
import com.example.Used_Inst_market.web.dto.category.brand.BrandInsertRequestDTO;
import com.example.Used_Inst_market.web.dto.category.brand.BrandSelectRequestDTO;
import com.example.Used_Inst_market.web.dto.category.brand.BrandUpdateRequestDTO;
import com.example.Used_Inst_market.web.vo.category.BrandVO;
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
    public BrandVO select(BrandSelectRequestDTO brandSelectRequestDTO) {
        Brand brand = brandRepository.findById(brandSelectRequestDTO.getBrandNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 브랜드가 없습니다."));

        return new BrandVO(brand);
    }

    @Transactional
    public List<BrandVO> selectAll() {
        return brandRepository.findAll().stream()
                .map(BrandVO::new)
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
