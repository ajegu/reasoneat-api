package reasoneatapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reasoneatapi.model.Month;

import java.util.UUID;

public interface MonthRepository extends JpaRepository<Month, UUID> {
}
