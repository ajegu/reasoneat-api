package reasoneatapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@Data
public class ProductDTO {
    @JsonProperty("product_id")
    private UUID id;

    private String name;

    private String image;

    @JsonProperty("header_text")
    private String headerText;

    @JsonProperty("footer_text")
    private String footerText;

    @JsonProperty("created_at")
    private Date createdAt;

    @JsonProperty("updated_at")
    private Date updatedAt;

    private CategoryDTO category;

    private Collection<MonthDTO> months;

}
