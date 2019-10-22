package reasoneatapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reasoneatapi.model.Category;

import javax.persistence.NonUniqueResultException;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    Optional<Category> findOneByName(String name) throws NonUniqueResultException;
}
