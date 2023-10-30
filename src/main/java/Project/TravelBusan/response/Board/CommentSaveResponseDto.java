package Project.TravelBusan.response.Board;


import Project.TravelBusan.domain.BoardComment;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class CommentSaveResponseDto {

    private Long boarId;
    private Long parentId;
    private Long id;
    private String content;
    private String writer;

    private List<BoardComment> children;

    public static CommentSaveResponseDto toDto(BoardComment boardComment) {
        return CommentSaveResponseDto.builder()
                .boarId(boardComment.getBoard().getId())
                .parentId(boardComment.getParent() == null ? null : boardComment.getParent().getId())
                .id(boardComment.getId())
                .content(boardComment.getContent())
                .writer(boardComment.getUser().getNickname())
                .children(boardComment.getChildren())
                .build();
    }
}
