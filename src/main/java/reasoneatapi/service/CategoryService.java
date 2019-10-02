package reasoneatapi.service;

import reasoneatapi.dto.CategoryDTO;
import reasoneatapi.model.Category;

public interface CategoryService {
    CategoryDTO save(CategoryDTO categoryDTO);
}
