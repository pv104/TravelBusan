package Project.TravelBusan.request.User;


import Project.TravelBusan.domain.User;
import Project.TravelBusan.request.AuthorityDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserLoginRequestDto {
    private String username;
    private String password;
}
