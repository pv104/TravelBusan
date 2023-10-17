package Project.TravelBusan.response;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;


@Data
@Builder
public class BoardSaveResponseDto {
    private Long id;
    private String title;
    private String content;
    private String nickname;
    private Long visit;
    private Long like;
    private Timestamp creDate;
}


