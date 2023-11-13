package Project.TravelBusan.response.Board;

import Project.TravelBusan.domain.Board;
import Project.TravelBusan.domain.BoardComment;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Data
public class BoardDetailResponseDto {
    private Long boardId;
    private String title;
    private String content;
    private String nickname;
    private Long visit;
    private Long likeCount;

    @JsonFormat(pattern = "yyyy.MM.dd hh:mm")
    private Timestamp creDate;

    private List<CommentResponseDto> comments; // 댓글

    public BoardDetailResponseDto(Board board) {
        boardId = board.getId();
        title = board.getTitle();
        content = board.getContent();
        nickname = board.getNickname();
        visit = board.getVisit();
        likeCount = board.getLikeCount();
        creDate = board.getCreDate();
        comments = new ArrayList<>();
        for (BoardComment boardComment : board.getComments()) {
            comments.add(new CommentResponseDto(boardComment));
        }
    }
}


