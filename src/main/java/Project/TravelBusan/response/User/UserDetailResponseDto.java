package Project.TravelBusan.response.User;

import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@Builder
public class UserDetailResponseDto {
    private Long id;
    private String username;
    private String nickname;
    private String email;
    private Timestamp creDate;

}
