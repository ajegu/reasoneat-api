package reasoneatapi.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private UUID id;

    private String name;

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
    private Category category;

    @ManyToMany
    @JoinTable(
            name = "product_month",
            joinColumns = {@JoinColumn(name = "month_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")}
    )
    private Collection<Month> months;

}
