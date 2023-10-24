package Project.TravelBusan.response;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Builder
public class UserListResponseDto {
    private Long id;
    private String username;
    private String nickname;
    private String email;
    private Timestamp creDate;


}
