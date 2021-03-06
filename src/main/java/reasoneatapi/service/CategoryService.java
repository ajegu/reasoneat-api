package reasoneatapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reasoneatapi.dto.CategoryDTO;
import reasoneatapi.exception.CategoryInvalidException;
import reasoneatapi.exception.CategoryNotFoundException;

import java.util.UUID;

public interface CategoryService {
    Page<CategoryDTO> findAll(Pageable pageable);
    CategoryDTO findOne(UUID id) throws CategoryNotFoundException;
    Boolean exist(CategoryDTO categoryDTO);
    CategoryDTO save(CategoryDTO categoryDTO) throws CategoryInvalidException;
    CategoryDTO update(CategoryDTO categoryDTO, UUID id) throws CategoryInvalidException;
    void delete(UUID id);
}
