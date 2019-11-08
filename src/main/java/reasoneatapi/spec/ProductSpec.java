package reasoneatapi.spec;

import org.springframework.data.jpa.domain.Specification;
import reasoneatapi.dto.ProductFilterDTO;
import reasoneatapi.model.Product;

public class ProductSpec {
    public static Specification<Product> filters(ProductFilterDTO productFilterDTO) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> {
            if (productFilterDTO.name != null) {
                return criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), "%" + productFilterDTO.name.toUpperCase() + "%");
            }
            return null;
        };
    }
}
