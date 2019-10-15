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

        // Contrôle de la taille du libellé
        if (categoryDTO.getName().length() > 0 && categoryDTO.getName().length() < 3) {
            errors.rejectValue("name", "name.too_short", "Le libellé doit contenir plus de 3 caractères");
        }

        if (categoryDTO.getName().length() > 50) {
            errors.rejectValue("name", "name.too_long", "Le libellé doit contenir moins de 50 caractères");
        }

        if (categoryDTO.getHeaderText().length() > 0 && categoryDTO.getHeaderText().length() < 50) {
            errors.rejectValue("headerText", "headerText.too_short", "Le texte de haut de page doit contenir plus de 50 caractères");
        }

        if (categoryDTO.getFooterText().length() > 0 && categoryDTO.getFooterText().length() < 50) {
            errors.rejectValue("footerText", "footerText.too_short", "Le texte de bas de page doit contenir plus de 50 caractères");
        }

    }
}
