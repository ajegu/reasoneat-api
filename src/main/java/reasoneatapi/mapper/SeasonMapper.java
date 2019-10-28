package reasoneatapi.mapper;

import org.mapstruct.Mapper;
import reasoneatapi.dto.SeasonDTO;
import reasoneatapi.model.Season;

@Mapper(componentModel = "spring")
public interface SeasonMapper {
    SeasonDTO seasonToSeasonDTO(Season season);
    Season seasonDTOToSeason(SeasonDTO seasonDTO);
}
