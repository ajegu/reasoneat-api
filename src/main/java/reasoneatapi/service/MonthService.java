package reasoneatapi.service;

import reasoneatapi.dto.MonthDTO;
import reasoneatapi.exception.MonthNotFoundException;

import java.util.UUID;

public interface MonthService {
    MonthDTO findOne(UUID id) throws MonthNotFoundException;
}
