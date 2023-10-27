package Project.TravelBusan.response.User;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserLoginResponseDto {
    private Long id;
    private String nickname;

}
