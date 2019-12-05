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
import javax.persistence.criteria.*;
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

        if (productFilterDTO.months != null) {
//            List<Month> monthList = new ArrayList<>();
//            for (String monthId: productFilterDTO.months) {
//                Optional<Month> optionalMonth = monthRepository.findById(UUID.fromString(monthId));
//                optionalMonth.ifPresent(monthList::add);
//            }

//            Subquery<Month> monthSubquery = criteriaQuery.subquery(Month.class);
//            Root<Month> monthRoot = monthSubquery.from(Month.class);
//            Join<Product, Month> productMonthJoin = monthRoot.joinCollection("products");
//            monthSubquery.select(productMonthJoin)
//                    .where(criteriaBuilder.equal(monthRoot.get(Month_.name), "Février"));
//
//            Root<Month> monthRoot = criteriaQuery.from(Month.class);
//            Join<Product, Month> productMonthJoin = root.joinCollection("months");
//
//            Subquery<UUID> uuidSubquery = criteriaQuery.subquery(UUID.class);
//
//
//
//            return criteriaBuilder.in(root.get(Product_.months)).value(monthList);
        }

        return null;
    }
}
