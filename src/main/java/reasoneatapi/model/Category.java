package reasoneatapi.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "name"))
@Data
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private UUID id;

    @Size(min = 3, max = 50, message = "Le libellé doit être compris entre {min} et {max} caractères")
    @NotNull(message = "Le libellé de la catégorie est obligatoire")
    private String name;

    @Column(name = "created_at")
    @NotNull(message = "La date de création de la catégorie est obligatoire")
    private Date createdAt;

    @Column(name = "header_text")
    @Size(min = 50, message = "Le texte doit faire au minimum {min} caractères")
    private String headerText;

    @Column(name = "footer_text")
    @Size(min = 50, message = "Le texte doit faire au minimum {min} caractères")
    private String footerText;

    @Column(name = "updated_at")
    private Date updatedAt;

    @OneToMany(mappedBy = "category")
    private Collection<Product> products;
}
