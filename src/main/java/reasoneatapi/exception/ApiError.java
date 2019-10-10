package reasoneatapi.exception;

import lombok.Data;

import java.util.Date;
import java.util.HashMap;

@Data
class ApiError {
    private Date timestamp;
    private int status;
    private HashMap<String, String> errors;
}
