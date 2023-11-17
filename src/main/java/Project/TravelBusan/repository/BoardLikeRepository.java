package Project.TravelBusan.repository;

import Project.TravelBusan.domain.Board;
import Project.TravelBusan.domain.BoardLike;
import Project.TravelBusan.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface BoardLikeRepository extends JpaRepository<BoardLike, Long> {
    Optional<BoardLike> findByUserAndBoard(User user, Board board);

}