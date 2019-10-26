package reasoneatapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reasoneatapi.dto.CategoryDTO;
import reasoneatapi.exception.CategoryInvalidException;

import java.util.UUID;

public interface CategoryService {
    Page<CategoryDTO> findAll(Pageable pageable);
    CategoryDTO findOne(UUID id);
    Boolean exist(CategoryDTO categoryDTO);
    CategoryDTO save(CategoryDTO categoryDTO) throws CategoryInvalidException;
    CategoryDTO update(CategoryDTO categoryDTO, UUID id);
    void delete(UUID id);
}
