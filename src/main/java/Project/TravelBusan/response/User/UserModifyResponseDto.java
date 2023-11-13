package Project.TravelBusan.response.User;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@Builder
public class UserModifyResponseDto {
    private Long id;
    private String username;
    private String nickname;
    private String email;
    @JsonFormat(pattern = "yyyy.MM.dd hh:mm")
    private Timestamp creDate;
}
