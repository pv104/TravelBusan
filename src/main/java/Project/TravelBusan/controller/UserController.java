package Project.TravelBusan.controller;

import Project.TravelBusan.request.User.UserLoginRequestDto;
import Project.TravelBusan.request.User.UserModifyRequestDto;
import Project.TravelBusan.request.User.UserRemoveRequestDto;
import Project.TravelBusan.response.User.UserListResponseDto;
import Project.TravelBusan.response.ResponseDto;
import Project.TravelBusan.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseDto<List<UserListResponseDto>> userList(){
        return userService.listUser();
    }

    @GetMapping("/{user-id}")
    public ResponseDto<UserListResponseDto> userDetail(@PathVariable("user-id") Long userId){
        return userService.detailUser(userId);
    }

    @PatchMapping("/{user-id}")
    public ResponseDto<UserListResponseDto> userModify(@PathVariable("user-id") Long userId, @RequestBody UserModifyRequestDto userModifyRequestDto) throws Exception {
       if(userModifyRequestDto.getId().equals(userId))
        return userService.modifyUser(userId, userModifyRequestDto);
       else {
           throw new IllegalAccessException("본인");
       }
    }

    @DeleteMapping("/{user-id}")
    public ResponseDto<Void> userRemove(@PathVariable("user-id") Long userId, @RequestBody UserRemoveRequestDto userRemoveRequestDto) throws Exception {
        if(userRemoveRequestDto.getId().equals(userId))
            return userService.removeUser(userId);
        else {
            throw new IllegalAccessException("본인");
        }
    }
    @GetMapping("/user")
    public ResponseEntity<UserLoginRequestDto> getMyUserInfo() {
        return ResponseEntity.ok(userService.getMyUserWithAuthorities());
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<UserLoginRequestDto> getUserInfo(@PathVariable String username) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!hasAdminRole(authentication)) {
            throw new IllegalAccessException("관리자");
        } else {
            return ResponseEntity.ok(userService.getUserWithAuthorities(username));
        }
    }
    private boolean hasAdminRole(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
    }
}
