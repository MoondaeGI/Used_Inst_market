package com.example.Used_Inst_market.service.brand;

import com.example.Used_Inst_market.domain.brand.Brand;
import com.example.Used_Inst_market.domain.brand.BrandRepository;
import com.example.Used_Inst_market.web.dto.brand.DeleteBrandRequestDTO;
import com.example.Used_Inst_market.web.dto.brand.InsertBrandRequestDTO;
import com.example.Used_Inst_market.web.dto.brand.SelectBrandRequestDTO;
import com.example.Used_Inst_market.web.dto.brand.UpdateBrandRequestDTO;
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
    public BrandInfoVO select(SelectBrandRequestDTO selectBrandRequestDTO) {
        Brand brand = brandRepository.findById(selectBrandRequestDTO.getBrandNo())
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
    public Long insert(InsertBrandRequestDTO insertBrandRequestDTO) {
        return brandRepository.save(insertBrandRequestDTO.toEntity()).getBrandNo();
    }

    @Transactional
    public Long update(UpdateBrandRequestDTO updateBrandRequestDTO) {
        Brand brand = brandRepository.findById(updateBrandRequestDTO.getBrandNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 브랜드가 없습니다."));

        brand.update(updateBrandRequestDTO.getLowerCategory(), updateBrandRequestDTO.getName());

        return brand.getBrandNo();
    }

    @Transactional
    public void delete(DeleteBrandRequestDTO deleteBrandRequestDTO) {
        Brand brand = brandRepository.findById(deleteBrandRequestDTO.getBrandNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 브렌드가 없습니다."));

        brandRepository.delete(brand);
    }
}
