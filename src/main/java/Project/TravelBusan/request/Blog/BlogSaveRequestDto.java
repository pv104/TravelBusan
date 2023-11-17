package Project.TravelBusan.request.Blog;

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
public class BlogSaveRequestDto {
    private String title;
    private String content;
    private String nickname;
    private Long visit;
    private Long likeCount;
    private User user;

    public void updateCreateBy(Long visit, Long likeCount, User user) {
        this.visit = visit;
        this.likeCount = likeCount;
        this.user = user;
    }
}
