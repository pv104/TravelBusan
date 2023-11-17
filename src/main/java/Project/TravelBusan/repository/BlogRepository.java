package Project.TravelBusan.repository;

import Project.TravelBusan.domain.Blog;
import Project.TravelBusan.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface BlogRepository extends JpaRepository<Blog, Long> {
    default Blog findByBlogOrElseThrow(Long blogId) {
        return findById(blogId).orElseThrow(() -> new IllegalStateException("해당 블로그가 존재하지 않습니다."));
    }

    @Modifying
    @Query("UPDATE Blog b SET b.visit = b.visit + 1 WHERE b.id = :blogId")
    void increaseVisit(@Param("blogId") Long questionId);

}
