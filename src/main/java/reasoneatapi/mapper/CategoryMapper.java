package reasoneatapi.mapper;

import org.mapstruct.Mapper;
import reasoneatapi.dto.CategoryDTO;
import reasoneatapi.model.Category;


@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDTO categoryToCategoryDTO(Category category);
    Category categoryDTOToCategory(CategoryDTO categoryDTO);
}

