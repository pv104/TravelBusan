package Project.TravelBusan.request.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRemoveRequestDto {
    private Long id;
    private String Username;
    private String email;
}
