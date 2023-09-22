package Project.TravelBusan.request;


import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberRequestDto {

    private String username;
    private String password;
    private String email;

}