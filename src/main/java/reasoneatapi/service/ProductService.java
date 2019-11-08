package reasoneatapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reasoneatapi.dto.ProductDTO;
import reasoneatapi.dto.ProductFilterDTO;
import reasoneatapi.exception.ProductInvalidException;
import reasoneatapi.exception.ProductNotFoundException;

import java.util.UUID;

public interface ProductService {
    Page<ProductDTO> findAll(ProductFilterDTO productFilterDTO, Pageable pageable);
    ProductDTO findOne(UUID id) throws ProductNotFoundException;
    ProductDTO save(ProductDTO productDTO) throws ProductInvalidException;
    Boolean exist(ProductDTO productDTO);
    ProductDTO update(ProductDTO productDTO, UUID id) throws ProductInvalidException, ProductNotFoundException;
    void delete(UUID id) throws ProductNotFoundException;
}
