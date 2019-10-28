package reasoneatapi.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;

@Entity
@Data
public class Month {

    @Id
    @GeneratedValue
    @Column(name = "month_id")
    private UUID id;

    private String name;

    @ManyToMany(mappedBy = "months")
    private Collection<Product> products;

    @ManyToOne
    @JoinColumn(name = "season_id")
    private Season season;
}
