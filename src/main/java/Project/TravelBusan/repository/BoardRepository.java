package Project.TravelBusan.repository;

import Project.TravelBusan.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BoardRepository extends JpaRepository<Board, Long> {

    default Board findByBoardOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new IllegalStateException("해당 게시글이 존재하지 않습니다."));
    }
}
