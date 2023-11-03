package Project.TravelBusan.response.User;

import lombok.Builder;
import lombok.Getter;
@Getter
@Builder
public class UserResponseDto
{
    private Long id;
    private String Username;
    private String email;

}

