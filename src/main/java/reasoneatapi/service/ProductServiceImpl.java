package reasoneatapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import reasoneatapi.dto.ProductDTO;
import reasoneatapi.exception.ProductInvalidException;
import reasoneatapi.exception.ProductNotFoundException;
import reasoneatapi.mapper.ProductMapper;
import reasoneatapi.model.Product;
import reasoneatapi.repository.ProductRepository;

import javax.persistence.NonUniqueResultException;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;

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

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        Product product = productMapper.productDTOToProduct(productDTO);
        product.setCreatedAt(new Date());

        this.validate(product);

        Product productSaved = productRepository.save(product);
        return productMapper.productToProductDTO(productSaved);
    }

    /**
     *
     * @param product Produit à valider
     */
    private void validate(Product product) {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        Set<ConstraintViolation<Product>> constraintViolations = validator.validate(product);
        if (constraintViolations.size() > 0) {
            throw new ProductInvalidException(constraintViolations.iterator().next().getMessage());
        }
    }

    @Override
    public Boolean exist(ProductDTO productDTO) {
        boolean exists;
        try {
            Optional<Product> result = productRepository.findOneByName(productDTO.getName());
            exists = result.isPresent() && !result.get().getId().equals(productDTO.getId()) ;
        } catch (NonUniqueResultException ex) {
            exists = true;
        }
        return exists;
    }

    @Override
    public ProductDTO update(ProductDTO productDTO, UUID id) throws ProductInvalidException {
        Optional<Product> productOptional = productRepository.findById(id);

        if (!productOptional.isPresent()) {
            throw new ProductNotFoundException(id);
        }

        Product product = productMapper.productDTOToProduct(productDTO);
        product.setId(id);
        product.setUpdatedAt(new Date());

        this.validate(product);

        Product productUpdated = productRepository.save(product);

        return productMapper.productToProductDTO(productUpdated);
    }
}
