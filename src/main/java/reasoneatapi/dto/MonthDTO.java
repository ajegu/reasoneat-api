package reasoneatapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Collection;
import java.util.UUID;

@Data
public class MonthDTO {
    @JsonProperty("month_id")
    private UUID id;

    private String name;

    private Collection<SeasonDTO> seasons;
}
