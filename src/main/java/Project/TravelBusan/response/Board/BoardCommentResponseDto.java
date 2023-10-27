package Project.TravelBusan.response.Board;


import Project.TravelBusan.domain.BoardComment;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
@Builder
public class BoardCommentResponseDto {

    private Long boarId;
    private Long parentId;
    private Long id;
    private String content;
    private String writer;

    private List<BoardComment> children = new ArrayList<>();

    public static BoardCommentResponseDto toDto(BoardComment boardComment) {
        return BoardCommentResponseDto.builder()
                .boarId(boardComment.getBoard().getId())
                .parentId(boardComment.getParent() == null ? null : boardComment.getParent().getId())
                .id(boardComment.getId())
                .content(boardComment.getContent())
                .writer(boardComment.getUser().getNickname())
                .children(boardComment.getChildren())
                .build();
    }
}
