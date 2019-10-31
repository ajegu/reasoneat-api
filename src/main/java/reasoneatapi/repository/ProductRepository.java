package reasoneatapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reasoneatapi.model.Product;

import javax.persistence.NonUniqueResultException;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Optional<Product> findOneByName(String name) throws NonUniqueResultException;
}
