package Project.TravelBusan.request;


import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberJoinRequestDto {

    private String nickname;
    private String username;
    private String password;
    private String passwordCheck;
    private String email;
}