package Project.TravelBusan.repository;

import Project.TravelBusan.domain.User;
import Project.TravelBusan.domain.Sights;
import Project.TravelBusan.exception.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SightsRepository extends JpaRepository<Sights,Long> {
    List<Sights> findByCity(String city);

    default Sights findByIdOrElseThrow(Long sightsId) {
        return findById(sightsId).orElseThrow(() ->
                new NotFoundException("해당 명소를 찾을 수 없습니다."));
    }
}
