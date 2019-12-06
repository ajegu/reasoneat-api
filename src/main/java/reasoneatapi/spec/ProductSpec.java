package reasoneatapi.spec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import reasoneatapi.dto.ProductFilterDTO;
import reasoneatapi.model.Category;
import reasoneatapi.model.Month;
import reasoneatapi.model.Product;
import reasoneatapi.model.Product_;
import reasoneatapi.repository.CategoryRepository;
import reasoneatapi.repository.MonthRepository;
import javax.persistence.criteria.*;
import java.util.*;

@Service
public class ProductSpec implements Specification<Product> {

    @Autowired
    private MonthRepository monthRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private ProductFilterDTO productFilterDTO;

    public void setProductFilterDTO(ProductFilterDTO productFilterDTO) {
        this.productFilterDTO = productFilterDTO;
    }

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicateList = new ArrayList<>();

        // Prédicat pour la recherche par libellé
        if (productFilterDTO.name != null && !productFilterDTO.name.isEmpty()) {
            Predicate namePredicate = criteriaBuilder.like(criteriaBuilder.upper(root.get(Product_.name)), "%" + productFilterDTO.name.toUpperCase() + "%");
            predicateList.add(namePredicate);
        }

        // Prédicat pour le filtre par mois
        if (productFilterDTO.months != null && !productFilterDTO.months.isEmpty()) {

            List<Month> monthList = new ArrayList<>();
            for (String monthId: productFilterDTO.months) {
                Optional<Month> optionalMonth = monthRepository.findById(UUID.fromString(monthId));
                optionalMonth.ifPresent(monthList::add);
            }

            Predicate monthsPredicate = root.join(Product_.months).in(monthList);
            criteriaQuery.distinct(true);
            predicateList.add(monthsPredicate);
        }

        // Prédicat pour le filtre catégorie
        if (productFilterDTO.categories != null && !productFilterDTO.categories.isEmpty()) {
            List<Category> categoryList = new ArrayList<>();
            for (String categoryId: productFilterDTO.categories) {
                Optional<Category> optionalCategory = categoryRepository.findById(UUID.fromString(categoryId));
                optionalCategory.ifPresent(categoryList::add);
            }

            predicateList.add(root.join(Product_.category).in(categoryList));
        }

        // Application des prédicats
        if (!predicateList.isEmpty())  {
            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
        }

        return null;
    }
}
