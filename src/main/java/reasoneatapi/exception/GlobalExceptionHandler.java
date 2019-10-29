package reasoneatapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({
            HttpMessageNotReadableException.class,
            CategoryNotFoundException.class,
            MethodArgumentNotValidException.class,
            CategoryInvalidException.class,
            ProductNotFoundException.class,
            ProductInvalidException.class
    })
    public final ResponseEntity<ApiError> handle(Exception ex, WebRequest request) {
        HashMap<String, String> errors = new HashMap<>();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if (ex instanceof CategoryNotFoundException) {
            status = HttpStatus.NOT_FOUND;
            errors.put("category.not_found", ex.getMessage());
        }

        if (ex instanceof MethodArgumentNotValidException) {
            status = HttpStatus.BAD_REQUEST;
            ((MethodArgumentNotValidException) ex).getBindingResult().getAllErrors().forEach(objectError -> errors.put(objectError.getCode(), objectError.getDefaultMessage()));
        }

        if (ex instanceof CategoryInvalidException) {
            status = HttpStatus.BAD_REQUEST;
            errors.put("category.invalid", ex.getMessage());
        }

        if (ex instanceof HttpMessageNotReadableException) {
            errors.put("request", "La requête est invalide");
        }

        if (ex instanceof ProductInvalidException) {
            status = HttpStatus.BAD_REQUEST;
            errors.put("product.invalid", ex.getMessage());
        }

        if (ex instanceof ProductNotFoundException) {
            status = HttpStatus.NOT_FOUND;
            errors.put("product.not_found", ex.getMessage());
        }

        ApiError apiError = new ApiError();
        apiError.setTimestamp(new Date());
        apiError.setStatus(status.value());
        apiError.setErrors(errors);

        return new ResponseEntity<>(apiError, status);
    }
}
