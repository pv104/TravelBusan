package Project.TravelBusan.response.Blog;

import Project.TravelBusan.domain.Blog;
import Project.TravelBusan.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlogDetailResponseDto {
    private String title;
    private String content;
    private String nickname;
    private Long visit;
    private Long likeCount;
    private String img;


    public BlogDetailResponseDto(Blog blog) {
        this.title = blog.getTitle();
        this.content = blog.getContent();
        this.nickname = blog.getNickname();
        this.visit = blog.getVisit();
        this.likeCount = blog.getLikeCount();
        this.img = blog.getImg();

    }
}
