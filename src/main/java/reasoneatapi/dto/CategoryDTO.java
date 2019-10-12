package reasoneatapi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Data
public class CategoryDTO {
    @ApiModelProperty(notes = "L'ID est automatiquement généré par la base de données")
    private UUID id;

    @ApiModelProperty(notes = "Libellé de la catégorie", required = true)
    private String name;

    private Date createdAt;
    private Date updatedAt;
}
