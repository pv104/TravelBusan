package Project.TravelBusan.response;

import Project.TravelBusan.domain.Board;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.stream.Collectors;


@Data
@Builder
public class BoardListResponseDto {
    private Long id;
    private String title;
    private String content;
    private String nickname;
    private Long visit;
    private Long likeCount;
    private Timestamp creDate;
}


