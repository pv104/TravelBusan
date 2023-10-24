package Project.TravelBusan.repository;

import Project.TravelBusan.domain.User;
import Project.TravelBusan.domain.Sights;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SightsRepository extends JpaRepository<Sights,Long> {
}
