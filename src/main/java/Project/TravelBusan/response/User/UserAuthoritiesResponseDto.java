package Project.TravelBusan.response.User;


import Project.TravelBusan.domain.User;
import Project.TravelBusan.request.AuthorityDto;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Builder
public class UserAuthoritiesResponseDto {
    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private Timestamp creDate;
    private Set<AuthorityDto> authorityDtoSet;

    public static UserAuthoritiesResponseDto from(User user) {
        if(user == null) return null;

        return UserAuthoritiesResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .creDate(user.getCreDate())
                .authorityDtoSet(user.getAuthorities().stream()
                        .map(authority -> AuthorityDto.builder().authorityName(authority.getAuthorityName()).build())
                        .collect(Collectors.toSet()))
                .build();
    }
}
