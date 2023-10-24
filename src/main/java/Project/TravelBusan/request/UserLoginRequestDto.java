package Project.TravelBusan.request;


import Project.TravelBusan.domain.User;
import Project.TravelBusan.domain.User;
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
    private String nickname;
    private Set<AuthorityDto> authorityDtoSet;

    public static UserLoginRequestDto from(User user) {
        if(user == null) return null;

        return UserLoginRequestDto.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .nickname(user.getNickname())
                .authorityDtoSet(user.getAuthorities().stream()
                        .map(authority -> AuthorityDto.builder().authorityName(authority.getAuthorityName()).build())
                        .collect(Collectors.toSet()))
                .build();
    }
}