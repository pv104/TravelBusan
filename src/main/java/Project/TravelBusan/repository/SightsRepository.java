package Project.TravelBusan.repository;

import Project.TravelBusan.domain.Member;
import Project.TravelBusan.domain.Sights;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SightsRepository extends JpaRepository<Sights,Long> {
}
