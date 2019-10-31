package reasoneatapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reasoneatapi.dto.MonthDTO;
import reasoneatapi.exception.MonthNotFoundException;
import reasoneatapi.mapper.MonthMapper;
import reasoneatapi.model.Month;
import reasoneatapi.repository.MonthRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class MonthServiceImpl implements MonthService {
    @Autowired
    private MonthRepository monthRepository;

    @Autowired
    private MonthMapper monthMapper;

    @Override
    public MonthDTO findOne(UUID id) throws MonthNotFoundException {
        Optional<Month> monthOptional = monthRepository.findById(id);

        if (!monthOptional.isPresent()) {
            throw new MonthNotFoundException(id);
        }

        return monthMapper.monthToMonthDTO(monthOptional.get());
    }
}
