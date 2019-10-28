package reasoneatapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.UUID;

@Data
public class SeasonDTO {
    @JsonProperty("season_id")
    private UUID id;

    private String name;
}
