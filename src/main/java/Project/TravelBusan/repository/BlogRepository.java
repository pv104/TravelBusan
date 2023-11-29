package Project.TravelBusan.repository;

import Project.TravelBusan.domain.Blog;
import Project.TravelBusan.domain.Board;
import Project.TravelBusan.exception.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface BlogRepository extends JpaRepository<Blog, Long> {
    default Blog findByBlogOrElseThrow(Long blogId) {
        return findById(blogId).orElseThrow(() -> new NotFoundException("해당 블로그를 찾을 수 없습니다."));
    }

    @Modifying
    @Query("UPDATE Blog b SET b.visit = b.visit + 1 WHERE b.id = :blogId")
    void increaseVisit(@Param("blogId") Long questionId);

    List<Blog> findByTitleContaining(String title);


    List<Blog> findByUserId(Long id);
}
