package reasoneatapi.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import reasoneatapi.dto.ProductDTO;

public class ProductValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ProductDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors,
                ProductError.NAME_NOT_NULL.getField(),
                ProductError.NAME_NOT_NULL.getCode(),
                ProductError.NAME_NOT_NULL.getMessage());

        ValidationUtils.rejectIfEmpty(errors,
                ProductError.IMAGE_NOT_NULL.getField(),
                ProductError.IMAGE_NOT_NULL.getCode(),
                ProductError.IMAGE_NOT_NULL.getMessage());

        ProductDTO productDTO = (ProductDTO) target;

        // TODO finir d'implémenter la validation
//        if (productDTO.getName() != null) {
//
//        }

    }
}
