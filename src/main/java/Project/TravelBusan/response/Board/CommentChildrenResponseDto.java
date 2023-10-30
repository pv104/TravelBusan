package Project.TravelBusan.response.Board;

import Project.TravelBusan.domain.BoardComment;
import lombok.Data;

@Data
public class CommentChildrenResponseDto {
    private Long commentId;
    private Long boarId;
    private Long parentId;
    private String content;
    private String writer;

    public CommentChildrenResponseDto(BoardComment boardComment) {
        commentId = boardComment.getId();
        boarId = boardComment.getBoard().getId();
        parentId = (boardComment.getParent() != null) ? boardComment.getParent().getId() : null;
        content = boardComment.getContent();
        writer = boardComment.getUser().getNickname();
    }
}
