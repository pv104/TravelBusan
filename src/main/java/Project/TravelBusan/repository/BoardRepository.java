package Project.TravelBusan.repository;

import Project.TravelBusan.domain.Board;
import Project.TravelBusan.exception.NotFoundException;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface BoardRepository extends JpaRepository<Board, Long> {
    default Board findByBoardOrElseThrow(Long boardId) {
        return findById(boardId).orElseThrow(() -> new NotFoundException("해당 게시글을 찾을 수 없습니다."));
    }

    @Modifying // jpa 동시성 문제로 -> sql로 처리
    @Query("UPDATE Board b SET b.visit = b.visit + 1 WHERE b.id = :boardId")
    void increaseVisit(@Param("boardId") Long questionId);


}
