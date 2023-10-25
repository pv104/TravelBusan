package Project.TravelBusan.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserModifyRequestDto {
    private Long id;
    private String password;
    private String email;
    private String nickname;

}