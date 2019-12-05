package reasoneatapi.spec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import reasoneatapi.dto.ProductFilterDTO;
import reasoneatapi.model.Month;
import reasoneatapi.model.Month_;
import reasoneatapi.model.Product;
import reasoneatapi.model.Product_;
import reasoneatapi.repository.MonthRepository;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.ListAttribute;
import java.util.*;

@Service
public class ProductSpec implements Specification<Product> {

    @Autowired
    private MonthRepository monthRepository;

    private ProductFilterDTO productFilterDTO;

    public void setProductFilterDTO(ProductFilterDTO productFilterDTO) {
        this.productFilterDTO = productFilterDTO;
    }

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

        if (productFilterDTO.months != null && !productFilterDTO.months.isEmpty()) {

            List<Month> monthList = new ArrayList<>();
            for (String monthId: productFilterDTO.months) {
                Optional<Month> optionalMonth = monthRepository.findById(UUID.fromString(monthId));
                optionalMonth.ifPresent(monthList::add);
            }

            Predicate predicate = root.join(Product_.months).in(monthList);
            criteriaQuery.distinct(true);
            return predicate;
        }

        return null;
    }
}
