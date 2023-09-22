package Project.TravelBusan.response;

import lombok.*;

@Getter
@Builder
public class MemberResponseDto {
    private Long id;
    private String username;
    private String email;

}
