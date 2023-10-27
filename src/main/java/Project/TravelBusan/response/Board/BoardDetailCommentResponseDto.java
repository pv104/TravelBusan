package Project.TravelBusan.response.Board;


import Project.TravelBusan.domain.BoardComment;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class BoardDetailCommentResponseDto {

    private Long boarId;
    private Long parentId;
    private String content;
    private String writer;

    public BoardDetailCommentResponseDto(BoardComment boardComment) {
        boarId = boardComment.getBoard().getId();
//        parentId = boardComment.getParent().getId();
        content = boardComment.getContent();
        writer = boardComment.getWriter();
    }

}
