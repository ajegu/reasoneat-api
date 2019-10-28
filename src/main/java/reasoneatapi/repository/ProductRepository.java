package reasoneatapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reasoneatapi.model.Product;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
