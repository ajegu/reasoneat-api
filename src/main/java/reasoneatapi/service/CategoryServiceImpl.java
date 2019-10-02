package reasoneatapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;
import reasoneatapi.dto.CategoryDTO;
import reasoneatapi.mapper.CategoryMapper;
import reasoneatapi.model.Category;
import reasoneatapi.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;

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
    public CategoryDTO save(CategoryDTO categoryDTO) {
        Category category = categoryMapper.categoryDTOToCategory(categoryDTO);
        Category savedCategory = categoryRepository.save(category);

        return categoryMapper.categoryToCategoryDTO(savedCategory);
    }
}
