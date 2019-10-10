package reasoneatapi.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import reasoneatapi.dto.CategoryDTO;
import reasoneatapi.service.CategoryService;

public class CategoryValidator implements Validator {

    private CategoryService categoryService;

    public CategoryValidator(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return CategoryDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        // Contrôle du libellé
        ValidationUtils.rejectIfEmpty(errors, "name", "name.not_null", "Le libellé de la catégorie est obligatoire");

        CategoryDTO categoryDTO = (CategoryDTO) target;

        // Contrôle de l'unicité du libellé
        if (categoryService.exists(categoryDTO)) {
            errors.reject("category.exists", "La catégorie existe déjà");
        }

    }
}
