package reasoneatapi.mapper;

import org.mapstruct.Mapper;
import reasoneatapi.dto.ProductDTO;
import reasoneatapi.model.Product;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO productToProductDTO(Product product);
    Product productDTOToProduct(ProductDTO productDTO);
    List<ProductDTO> listProductToListProductDTO(List<Product> productList);
}
