package Project.TravelBusan.response.Board;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;


@Data
@Builder
public class BoardResponseDto {
    private Long id;
    private String title;
    private String content;
    private String nickname;
    private Long visit;
    private Long likeCount;

    @JsonFormat(pattern = "yyyy.MM.dd hh:mm", timezone = "Asia/Seoul")
    private Timestamp creDate;
}


