package Project.TravelBusan.repository;

import Project.TravelBusan.domain.User;
import Project.TravelBusan.domain.Sights;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SightsRepository extends JpaRepository<Sights,Long> {
    List<Sights> findByCity(String city);

    default Sights findByIdOrElseThrow(Long sightsId) {
        return findById(sightsId).orElseThrow(() ->
                new IllegalStateException("존재하지 명소 입니다."));
    }
}
