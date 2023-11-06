package Project.TravelBusan.controller;

import Project.TravelBusan.jwt.JwtFilter;
import Project.TravelBusan.jwt.TokenProvider;
import Project.TravelBusan.request.TokenDto;
import Project.TravelBusan.request.User.UserJoinRequestDto;
import Project.TravelBusan.request.User.UserLoginRequestDto;
import Project.TravelBusan.request.User.UserModifyRequestDto;
import Project.TravelBusan.response.User.UserListResponseDto;
import Project.TravelBusan.response.ResponseDto;
import Project.TravelBusan.response.User.UserLoginResponseDto;
import Project.TravelBusan.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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
    public ResponseDto<UserListResponseDto> userModify(@PathVariable("user-id") Long userId, @RequestBody UserModifyRequestDto userModifyRequestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // 현재 토큰에 있는사용자 정보를 가져옴
        String username = authentication.getName();
        return userService.modifyUser(userId, userModifyRequestDto, username);
       }

    @DeleteMapping("/{user-id}")
    public ResponseDto<Void> userRemove(@PathVariable("user-id") Long userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // 현재 토큰에 있는사용자 정보를 가져옴
        String username = authentication.getName();
        return userService.removeUser(userId, username);
    }

    @GetMapping("/user")
    public ResponseEntity<UserLoginRequestDto> getMyUserInfo() {
        return ResponseEntity.ok(userService.getMyUserWithAuthorities());
    }

    @PostMapping("/login")
    public ResponseDto<TokenDto> userLogin(@Valid @RequestBody UserLoginRequestDto userLoginRequestDto) {
        return userService.login(userLoginRequestDto);
    }

    @PostMapping("/signup")
    public ResponseDto<UserLoginResponseDto> userAdd(@RequestBody UserJoinRequestDto userJoinRequestDto) {
        return userService.join(userJoinRequestDto);
    }

    @GetMapping("/check")
    public void check (){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("nowUser : {}", authentication.getName());
        return;
    }
}
