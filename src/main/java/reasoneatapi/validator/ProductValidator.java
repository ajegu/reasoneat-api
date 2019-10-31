package reasoneatapi.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import reasoneatapi.dto.MonthDTO;
import reasoneatapi.dto.ProductDTO;
import reasoneatapi.exception.CategoryNotFoundException;
import reasoneatapi.exception.MonthNotFoundException;
import reasoneatapi.service.CategoryService;
import reasoneatapi.service.MonthService;
import reasoneatapi.service.ProductService;

public class ProductValidator implements Validator {

    private ProductService productService;

    private CategoryService categoryService;

    private MonthService monthService;

    public ProductValidator(ProductService productService, CategoryService categoryService, MonthService monthService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.monthService = monthService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return ProductDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        // Libellé du produit obligatoire
        ValidationUtils.rejectIfEmpty(errors,
                ProductError.NAME_NOT_NULL.getField(),
                ProductError.NAME_NOT_NULL.getCode(),
                ProductError.NAME_NOT_NULL.getMessage());

        // Lien de l'image obligatoire
        ValidationUtils.rejectIfEmpty(errors,
                ProductError.IMAGE_NOT_NULL.getField(),
                ProductError.IMAGE_NOT_NULL.getCode(),
                ProductError.IMAGE_NOT_NULL.getMessage());

        // Categorie obligatoire
        ValidationUtils.rejectIfEmpty(errors,
                ProductError.CATEGORY_NOT_NULL.getField(),
                ProductError.CATEGORY_NOT_NULL.getCode(),
                ProductError.CATEGORY_NOT_NULL.getMessage());

        // Mois obligatoire
        ValidationUtils.rejectIfEmpty(errors,
                ProductError.MONTHS_NOT_NULL.getField(),
                ProductError.MONTHS_NOT_NULL.getCode(),
                ProductError.MONTHS_NOT_NULL.getMessage());


        ProductDTO productDTO = (ProductDTO) target;

        // Validation du libellé du produit
        if (productDTO.getName() != null) {
            // Vérification de l'existance du produit
            if (productService.exist(productDTO)) {
                errors.reject(ProductError.ALREADY_EXIST.getCode(),
                        ProductError.ALREADY_EXIST.getMessage());
            }

            // Longueur minimale du libellé du produit
            if (productDTO.getName().length() < 3) {
                errors.rejectValue(ProductError.NAME_TOO_SHORT.getField(),
                        ProductError.NAME_TOO_SHORT.getCode(),
                        ProductError.NAME_TOO_SHORT.getMessage());
            }

            // Longueur maximale du libellé du produit
            if (productDTO.getName().length() > 50) {
                errors.rejectValue(ProductError.NAME_TOO_LONG.getField(),
                        ProductError.NAME_TOO_LONG.getCode(),
                        ProductError.NAME_TOO_LONG.getMessage());
            }
        }

        // Validation du lien de l'image du produit
        if (productDTO.getImage() != null) {
            // Longeur maximale du lien de l'image
            if (productDTO.getImage().length() > 255) {
                errors.rejectValue(ProductError.IMAGE_TOO_LONG.getField(),
                        ProductError.IMAGE_TOO_LONG.getCode(),
                        ProductError.IMAGE_TOO_LONG.getMessage());
            }
        }

        // Validation du texte de haut de page
        if (productDTO.getHeaderText() != null && productDTO.getHeaderText().length() > 0) {
            // Longeur minimale du texte de haut de page
            if (productDTO.getHeaderText().length() < 50) {
                errors.rejectValue(ProductError.HEADER_TEXT_TOO_SHORT.getField(),
                        ProductError.HEADER_TEXT_TOO_SHORT.getCode(),
                        ProductError.HEADER_TEXT_TOO_SHORT.getMessage());
            }
        }

        // Validation du texte de bas de page
        if (productDTO.getFooterText() != null && productDTO.getFooterText().length() > 0) {
            // Longeur minimale du texte de bas de page
            if (productDTO.getFooterText().length() < 50) {
                errors.rejectValue(ProductError.FOOTER_TEXT_TOO_SHORT.getField(),
                        ProductError.FOOTER_TEXT_TOO_SHORT.getCode(),
                        ProductError.FOOTER_TEXT_TOO_SHORT.getMessage());
            }
        }

        // Validation de la catégory
        if (productDTO.getCategory() != null) {
            // Existance de la catégorie en base de données
            try {
                categoryService.findOne(productDTO.getCategory().getId());
            } catch (CategoryNotFoundException ex) {
                errors.rejectValue(ProductError.CATEGORY_INVALID.getField(),
                        ProductError.CATEGORY_INVALID.getCode(),
                        ProductError.CATEGORY_INVALID.getMessage());
            }
        }

        // Validation des mois
        if (productDTO.getMonths() != null) {
            // Nombre de mois minimum
            if (productDTO.getMonths().size() == 0) {
                errors.rejectValue(ProductError.MONTHS_TOO_SHORT.getField(),
                        ProductError.MONTHS_TOO_SHORT.getCode(),
                        ProductError.MONTHS_TOO_SHORT.getMessage());
            }

            // Existance des mois en base de données
            for (MonthDTO monthDTO: productDTO.getMonths()) {
                try {
                    monthService.findOne(monthDTO.getId());
                } catch (MonthNotFoundException ex) {
                    errors.rejectValue(ProductError.MONTHS_INVALID.getField(),
                            ProductError.MONTHS_INVALID.getCode(),
                            ProductError.MONTHS_INVALID.getMessage());
                    break;
                }
            }
        }
    }
}
