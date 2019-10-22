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

    @ApiModelProperty(notes = "Lien vers l'image de la catégorie")
    private String image;

    @ApiModelProperty(notes = "Texte en haut de page de la catégorie")
    private String headerText;

    @ApiModelProperty(notes = "Texte en bas de page de la catégorie")
    private String footerText;

    private Date createdAt;
    private Date updatedAt;
}
