package reasoneatapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reasoneatapi.dto.CategoryDTO;

public interface CategoryService {
    Page<CategoryDTO> findAll(Pageable pageable);
    CategoryDTO save(CategoryDTO categoryDTO);
}
