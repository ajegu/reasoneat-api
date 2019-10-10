package reasoneatapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reasoneatapi.dto.CategoryDTO;

import java.util.UUID;

public interface CategoryService {
    Page<CategoryDTO> findAll(Pageable pageable);
    CategoryDTO findOne(UUID id);
    Boolean exists(CategoryDTO categoryDTO);
    CategoryDTO save(CategoryDTO categoryDTO);
    CategoryDTO update(CategoryDTO categoryDTO, UUID id);
    void delete(UUID id);
}
