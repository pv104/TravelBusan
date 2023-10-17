package Project.TravelBusan.response;

import lombok.Builder;
import lombok.Getter;
@Getter
@Builder
public class UserResponseDto
{
    private Long id;
    private String username;
    private String email;

}

