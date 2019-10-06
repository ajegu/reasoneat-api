package reasoneatapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import reasoneatapi.model.Error;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({CategoryNotFoundException.class})
    public final ResponseEntity<Error> handle(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if (ex instanceof CategoryNotFoundException) {
            status = HttpStatus.NOT_FOUND;
        }

        Error error = new Error();
        error.setMessage(ex.getMessage());

        return new ResponseEntity<>(error, status);
    }
}
