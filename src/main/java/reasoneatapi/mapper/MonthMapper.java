package reasoneatapi.mapper;

import org.mapstruct.Mapper;
import reasoneatapi.dto.MonthDTO;
import reasoneatapi.model.Month;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MonthMapper {
    MonthDTO monthToMonthDTO(Month month);
    Month monthDTOToMonth(MonthDTO monthDTO);
    List<MonthDTO> listMonthToListMonthDTO(List<Month> monthList);
}
