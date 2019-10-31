package reasoneatapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class CategoryDTO {
    @ApiModelProperty(notes = "L'ID est automatiquement généré par la base de données")
    @JsonProperty("category_id")
    private UUID id;

    @ApiModelProperty(notes = "Libellé de la catégorie", required = true)
    private String name;

    @ApiModelProperty(notes = "Lien vers l'image de la catégorie")
    private String image;

    @ApiModelProperty(notes = "Texte en haut de page de la catégorie")
    @JsonProperty("header_text")
    private String headerText;

    @ApiModelProperty(notes = "Texte en bas de page de la catégorie")
    @JsonProperty("footer_text")
    private String footerText;

    @JsonProperty("created_at")
    private Date createdAt;

    @JsonProperty("updated_at")
    private Date updatedAt;
}
