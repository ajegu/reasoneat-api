package reasoneatapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reasoneatapi.dto.CategoryDTO;
import reasoneatapi.mapper.CategoryMapper;
import reasoneatapi.model.Category;
import reasoneatapi.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {
        Category category = categoryMapper.categoryDTOToCategory(categoryDTO);
        Category savedCategory = categoryRepository.save(category);

        return categoryMapper.categoryToCategoryDTO(savedCategory);
    }
}
