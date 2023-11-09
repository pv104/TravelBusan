package Project.TravelBusan.request.Board;


import Project.TravelBusan.domain.BoardComment;
import lombok.*;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardCommentRequestDto {
    private String content;
    private BoardComment parent;

}
