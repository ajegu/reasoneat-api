package reasoneatapi.exception;

public class ProductInvalidException extends RuntimeException {
    public ProductInvalidException(String message) {
        super(message);
    }
}
