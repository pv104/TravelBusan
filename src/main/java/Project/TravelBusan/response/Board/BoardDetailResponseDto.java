package Project.TravelBusan.response.Board;

import Project.TravelBusan.domain.Board;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;


@Data
public class BoardDetailResponseDto {
    private Long boardId;
    private String title;
    private String content;
    private String nickname;
    private Long visit;
    private Long likeCount;
    private Timestamp creDate;

    private List<BoardDetailCommentResponseDto> comments;

    @Builder
    public BoardDetailResponseDto(Board board) {
        boardId = board.getId();
        title = board.getTitle();
        content = board.getContent();
        nickname = board.getNickname();
        visit = board.getVisit();
        likeCount = board.getLikeCount();
        creDate = board.getCreDate();
        comments = board.getComments().stream()
                .map(boardComment -> new BoardDetailCommentResponseDto(boardComment))
                .collect(Collectors.toList());
    }
}


