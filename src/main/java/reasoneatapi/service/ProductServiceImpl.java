package reasoneatapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import reasoneatapi.dto.ProductDTO;
import reasoneatapi.exception.ProductNotFoundException;
import reasoneatapi.mapper.ProductMapper;
import reasoneatapi.model.Product;
import reasoneatapi.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<Product> productPage = productRepository.findAll(pageable);

        List<ProductDTO> productDTOList = productMapper.listProductToListProductDTO(productPage.getContent());

        return PageableExecutionUtils.getPage(productDTOList, pageable, productPage::getTotalElements);
    }

    @Override
    public ProductDTO findOne(UUID id) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (!optionalProduct.isPresent()) {
            throw new ProductNotFoundException(id);
        }

        return productMapper.productToProductDTO(optionalProduct.get());
    }
}
