package reasoneatapi.validator;

import io.swagger.annotations.Example;
import io.swagger.annotations.ExampleProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import reasoneatapi.dto.CategoryDTO;
import reasoneatapi.model.Category;
import reasoneatapi.service.CategoryService;

public class CategoryValidator implements Validator {


    private String errorCode;

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
        ValidationUtils.rejectIfEmpty(errors, CategoryError.NAME_NOT_NULL.getField(), CategoryError.NAME_NOT_NULL.getCode(), CategoryError.NAME_NOT_NULL.getMessage());

        CategoryDTO categoryDTO = (CategoryDTO) target;

        // Contrôle de l'unicité du libellé
        if (categoryService.exists(categoryDTO)) {
            errors.reject(CategoryError.ALREADY_EXIST.getCode(), CategoryError.ALREADY_EXIST.getMessage());
        }

        // Contrôle de la taille du libellé
        if (!categoryDTO.getName().isEmpty() && categoryDTO.getName().length() < 3) {
            errors.rejectValue(CategoryError.NAME_TOO_SHORT.getField(), CategoryError.NAME_TOO_SHORT.getCode(), CategoryError.NAME_TOO_SHORT.getMessage());
        }

        if (categoryDTO.getName().length() > 50) {
            errors.rejectValue(CategoryError.NAME_TOO_LONG.getField(), CategoryError.NAME_TOO_LONG.getCode(), CategoryError.NAME_TOO_LONG.getMessage());
        }

        // Contrôle du texte de haut de page
        if (!categoryDTO.getHeaderText().isEmpty() && categoryDTO.getHeaderText().length() < 50) {
            errors.rejectValue(CategoryError.HEADER_TEXT_TOO_SHORT.getField(), CategoryError.HEADER_TEXT_TOO_SHORT.getCode(), CategoryError.HEADER_TEXT_TOO_SHORT.getMessage());
        }

        // Contrôle du texte de bas de page
        if (!categoryDTO.getFooterText().isEmpty() && categoryDTO.getFooterText().length() < 50) {
            errors.rejectValue(CategoryError.FOOTER_TEXT_TOO_SHORT.getField(), CategoryError.FOOTER_TEXT_TOO_SHORT.getCode(), CategoryError.FOOTER_TEXT_TOO_SHORT.getMessage());
        }

        // Contrôle du lien de l'image
        if (!categoryDTO.getImage().isEmpty() && categoryDTO.getImage().length() > 255) {
            errors.rejectValue(CategoryError.IMAGE_TOO_LONG.getField(), CategoryError.IMAGE_TOO_LONG.getCode(), CategoryError.IMAGE_TOO_LONG.getMessage());
        }

    }
}
