package reasoneatapi.exception;

import java.util.UUID;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(UUID id) {
        super("Impossible de trouver le produit " + id);
    }
}
