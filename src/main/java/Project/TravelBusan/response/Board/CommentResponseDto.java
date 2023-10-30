package Project.TravelBusan.response.Board;


import Project.TravelBusan.domain.BoardComment;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class CommentResponseDto {

    private Long commentId;
    private Long boarId;
    private Long parentId;
    private String content;
    private String writer;
    private List<CommentChildrenResponseDto> children; // 대댓글


    public CommentResponseDto(BoardComment boardComment) {
        commentId = boardComment.getId();
        boarId = boardComment.getBoard().getId();
        parentId = (boardComment.getParent() != null) ? boardComment.getParent().getId() : null;
        content = boardComment.getContent();
        writer = boardComment.getUser().getNickname();
        // 대댓글 설정
        children = new ArrayList<>();
        for (BoardComment childComment : boardComment.getChildren()) {
            children.add(new CommentChildrenResponseDto(childComment));
        }
    }

}
