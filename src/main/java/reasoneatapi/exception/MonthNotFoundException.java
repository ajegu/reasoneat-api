package reasoneatapi.exception;

import java.util.UUID;

public class MonthNotFoundException extends RuntimeException {
    public MonthNotFoundException(UUID id) {
        super("Impossible de trouver le mois " + id);
    }
}
