package com.example.Used_Inst_market.service.product;

import com.example.Used_Inst_market.domain.product.Product;
import com.example.Used_Inst_market.domain.product.ProductRepository;
import com.example.Used_Inst_market.web.dto.product.ProductDeleteRequestDTO;
import com.example.Used_Inst_market.web.dto.product.ProductInsertRequestDTO;
import com.example.Used_Inst_market.web.dto.product.ProductSelectRequestDTO;
import com.example.Used_Inst_market.web.dto.product.ProductUpdateRequestDTO;
import com.example.Used_Inst_market.web.vo.product.ProductInfoVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional
    public ProductInfoVO select(ProductSelectRequestDTO productSelectRequestDTO) {
        Product product = productRepository.findById(productSelectRequestDTO.getProductNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다."));

        return new ProductInfoVO(product);
    }

    @Transactional
    public List<ProductInfoVO> selectAll() {
        return productRepository.findAll().stream()
                .map(ProductInfoVO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long insert(ProductInsertRequestDTO productInsertRequestDTO) {
        return productRepository.save(productInsertRequestDTO.toEntity()).getProductNo();
    }

    @Transactional
    public Long update(ProductUpdateRequestDTO productUpdateRequestDTO) {
        Product product = productRepository.findById(productUpdateRequestDTO.getProductNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다."));

        product.update(productUpdateRequestDTO.getBrand(), productUpdateRequestDTO.getName(),
                productUpdateRequestDTO.getPrice());

        return productUpdateRequestDTO.getProductNo();
    }

    @Transactional
    public void delete(ProductDeleteRequestDTO productDeleteRequestDTO) {
        Product product = productRepository.findById(productDeleteRequestDTO.getProductNo())
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다."));

        productRepository.delete(product);
    }
}
