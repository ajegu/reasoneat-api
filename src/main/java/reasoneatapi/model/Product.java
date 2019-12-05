package reasoneatapi.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private UUID id;

    @NotNull(message = "Le libellé du produit est obligatoire")
    @Size(min = 3, max = 50, message = "Le libellé du produit doit être compris entre {min} et {max} caractères")
    private String name;

    @NotNull(message = "L'image du produit est obligatoire")
    @Size(max = 255, message = "Le lien de l'image ne doit pas dépasser {max} caractères")
    private String image;

    @Column(name = "header_text")
    private String headerText;

    @Column(name = "footer_text")
    private String footerText;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @NotNull(message = "La catégorie est obligatoire")
    private Category category;

    @ManyToMany
    @JoinTable(
            name = "product_month",
            joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "month_id")}
    )
    @NotNull(message = "Les mois sont obligatoires")
    @NotEmpty(message = "Au moins un mois doit être renseigné")
    private List<Month> months;

}
