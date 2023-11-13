package Project.TravelBusan.repository;

import Project.TravelBusan.domain.User;
import Project.TravelBusan.domain.Sights;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SightsRepository extends JpaRepository<Sights,Long> {
    List<Sights> findByCity(String city);
}
