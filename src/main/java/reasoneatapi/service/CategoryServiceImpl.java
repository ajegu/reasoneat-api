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

        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        categoryPage.getContent().forEach(category -> {
            CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);
            categoryDTOList.add(categoryDTO);
        });

        return PageableExecutionUtils.getPage(categoryDTOList, pageable, categoryPage::getTotalElements);
    }

    @Override
    public CategoryDTO findOne(UUID id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (!category.isPresent()) {
            throw new CategoryNotFoundException(id);
        }
        return categoryMapper.categoryToCategoryDTO(category.get());
    }

    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {
        Category category = categoryMapper.categoryDTOToCategory(categoryDTO);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Category>> constraintViolations = validator.validate(category);
        if (constraintViolations.size() > 0) {
            throw new CategoryInvalidException(constraintViolations.iterator().next().getMessage());
        }

        Category savedCategory = categoryRepository.save(category);

        return categoryMapper.categoryToCategoryDTO(savedCategory);
    }

    @Override
    public CategoryDTO update(CategoryDTO categoryDTO, UUID id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (!category.isPresent()) {
            throw new CategoryNotFoundException(id);
        }

        category.get().setName(categoryDTO.getName());
        categoryRepository.save(category.get());

        return categoryMapper.categoryToCategoryDTO(category.get());
    }

    @Override
    public void delete(UUID id) {
        if (categoryRepository.findById(id).isPresent()) {
            categoryRepository.deleteById(id);
        }
    }

    @Override
    public Boolean exists(CategoryDTO categoryDTO) {
        return categoryRepository.findOneByName(categoryDTO.getName()).isPresent();
    }
}
