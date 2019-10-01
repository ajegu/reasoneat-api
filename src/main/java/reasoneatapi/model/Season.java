package reasoneatapi.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;

@Entity
public class Season {

    @Id
    @GeneratedValue
    @Column(name = "season_id")
    private UUID id;

    private String name;

    @OneToMany(mappedBy = "season")
    private Collection<Month> months;
}
