package Project.TravelBusan.request;

import Project.TravelBusan.domain.Member;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BoardRequestDto {
    private String title;
    private String content;
    private String nickname;
    private Long visit;
    private Long likeCount;
    private Member member;

    public void updateCreateBy(Long visit,Long likeCount,Member member) {
        this.visit = visit;
        this.likeCount = likeCount;
        this.member = member;
    }
}
