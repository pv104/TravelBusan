package Project.TravelBusan.repository;


import Project.TravelBusan.domain.BoardComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardCommentRepository extends JpaRepository<BoardComment, Long> {
}

