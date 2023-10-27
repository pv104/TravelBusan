package Project.TravelBusan.request.User;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserRemoveRequestDto {
    private Long id;
    private String Username;
    private String email;
}
