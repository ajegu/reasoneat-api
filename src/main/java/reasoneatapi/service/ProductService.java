package reasoneatapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reasoneatapi.dto.ProductDTO;
import reasoneatapi.exception.ProductNotFoundException;

import java.util.UUID;

public interface ProductService {
    Page<ProductDTO> findAll(Pageable pageable);
    ProductDTO findOne(UUID id) throws ProductNotFoundException;
    ProductDTO save(ProductDTO productDTO);
}
