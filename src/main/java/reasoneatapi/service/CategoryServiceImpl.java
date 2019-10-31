package reasoneatapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import reasoneatapi.dto.CategoryDTO;
import reasoneatapi.exception.CategoryInvalidException;
import reasoneatapi.exception.CategoryNotFoundException;
import reasoneatapi.mapper.CategoryMapper;
import reasoneatapi.model.Category;
import reasoneatapi.repository.CategoryRepository;

import javax.persistence.NonUniqueResultException;
import javax.validation.*;
import java.util.*;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Page<CategoryDTO> findAll(Pageable pageable) {
        Page<Category> categoryPage = categoryRepository.findAll(pageable);

        List<CategoryDTO> categoryDTOList = categoryMapper.listCategoryToListCategoryDTO(categoryPage.getContent());

        return PageableExecutionUtils.getPage(categoryDTOList, pageable, categoryPage::getTotalElements);
    }

    @Override
    public CategoryDTO findOne(UUID id) throws CategoryNotFoundException {
        Optional<Category> category = categoryRepository.findById(id);
        if (!category.isPresent()) {
            throw new CategoryNotFoundException(id);
        }
        return categoryMapper.categoryToCategoryDTO(category.get());
    }

    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {
        Category category = categoryMapper.categoryDTOToCategory(categoryDTO);
        category.setCreatedAt(new Date());

        this.validate(category);
        Category savedCategory = categoryRepository.save(category);

        return categoryMapper.categoryToCategoryDTO(savedCategory);
    }

    @Override
    public CategoryDTO update(CategoryDTO categoryDTO, UUID id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (!category.isPresent()) {
            throw new CategoryNotFoundException(id);
        }
        Category categoryUpdated = categoryMapper.categoryDTOToCategory(categoryDTO);
        categoryUpdated.setId(id);
        categoryUpdated.setUpdatedAt(new Date());

        this.validate(categoryUpdated);
        categoryRepository.save(categoryUpdated);

        return categoryMapper.categoryToCategoryDTO(categoryUpdated);
    }

    @Override
    public void delete(UUID id) {
        if (categoryRepository.findById(id).isPresent()) {
            categoryRepository.deleteById(id);
        }
    }

    @Override
    public Boolean exist(CategoryDTO categoryDTO) {
        boolean exists;
        try {
            Optional<Category> result = categoryRepository.findOneByName(categoryDTO.getName());
            exists = result.isPresent() && !result.get().getId().equals(categoryDTO.getId()) ;
        } catch (NonUniqueResultException ex) {
            exists = true;
        }
        return exists;
    }

    private void validate(Category category) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Category>> constraintViolations = validator.validate(category);
        if (constraintViolations.size() > 0) {
            throw new CategoryInvalidException(constraintViolations.iterator().next().getMessage());
        }
    }
}
