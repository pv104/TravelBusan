package Project.TravelBusan.repository;

import Project.TravelBusan.domain.Board;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface BoardRepository extends JpaRepository<Board, Long> {
    default Board findByBoardOrElseThrow(Long boardId) {
        return findById(boardId).orElseThrow(() -> new IllegalStateException("해당 게시글이 존재하지 않습니다."));
    }

    @Modifying // jpa 동시성 문제로 -> sql로 처리
    @Query("UPDATE Board b SET b.visit = b.visit + 1 WHERE b.id = :boardId")
    void increaseVisit(@Param("boardId") Long questionId);


}
