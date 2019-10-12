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
    @NotNull
    private String name;

    @Column(name = "created_at")
    @NotNull
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @OneToMany(mappedBy = "category")
    private Collection<Product> products;
}
