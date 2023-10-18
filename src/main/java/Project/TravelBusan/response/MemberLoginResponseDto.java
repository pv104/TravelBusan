package Project.TravelBusan.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberLoginResponseDto {
    private Long id;
    private String nickname;

}
