package reasoneatapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reasoneatapi.dto.ProductDTO;

public interface ProductService {
    Page<ProductDTO> findAll(Pageable pageable);
}
