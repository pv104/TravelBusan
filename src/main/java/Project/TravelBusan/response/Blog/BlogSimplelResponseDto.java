package Project.TravelBusan.response.Blog;

import Project.TravelBusan.domain.Blog;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlogSimplelResponseDto {
    private String title;
    private String nickname;
    private Timestamp creDate;


    public BlogSimplelResponseDto(Blog blog) {
        this.title = blog.getTitle();
        this.nickname = blog.getNickname();
        this.creDate = blog.getCreDate();

    }
}
