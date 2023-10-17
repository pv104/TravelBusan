package Project.TravelBusan.request;


import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberJoinRequestDto {

    private String username;
    private String password;
    private String email;
    private String nickname;

}