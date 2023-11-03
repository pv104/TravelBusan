package Project.TravelBusan.request.Board;


import Project.TravelBusan.domain.BoardComment;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class BoardCommentRequestDto {
    private String content;
    private BoardComment parent;

}
