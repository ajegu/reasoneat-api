package reasoneatapi.exception;

import java.util.UUID;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(UUID id) {
        super("Impossible de trouver la categorie " + id);
    }
}
