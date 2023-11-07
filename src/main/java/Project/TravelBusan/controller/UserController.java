package Project.TravelBusan.controller;

import Project.TravelBusan.jwt.JwtFilter;
import Project.TravelBusan.jwt.TokenProvider;
import Project.TravelBusan.request.TokenDto;
import Project.TravelBusan.request.User.UserJoinRequestDto;
import Project.TravelBusan.request.User.UserLoginRequestDto;
import Project.TravelBusan.request.User.UserModifyRequestDto;
import Project.TravelBusan.response.User.UserDetailResponseDto;
import Project.TravelBusan.response.User.UserListResponseDto;
import Project.TravelBusan.response.ResponseDto;
import Project.TravelBusan.response.User.UserLoginResponseDto;
import Project.TravelBusan.response.User.UserModifyResponseDto;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseDto<UserDetailResponseDto> userDetail(){
        return userService.detailUser();
    }

    @PatchMapping
    public ResponseDto<UserModifyResponseDto> userModify(@RequestBody UserModifyRequestDto userModifyRequestDto) {
        return userService.modifyUser(userModifyRequestDto);
       }

    @DeleteMapping
    public ResponseDto<Void> userRemove() {
        return userService.removeUser();
    }

    @GetMapping("/list")
    public ResponseDto<List<UserListResponseDto>> userList(){
        return userService.listUser();
    }

    @GetMapping("/user")
    public ResponseEntity<UserLoginRequestDto> getMyUserInfo() {
        return ResponseEntity.ok(userService.getMyUserWithAuthorities());
    }

    @PostMapping("/login")
    public ResponseDto<TokenDto> userLogin(@RequestBody UserLoginRequestDto userLoginRequestDto) {
        return userService.login(userLoginRequestDto);
    }

    @PostMapping("/signup")
    public ResponseDto<UserLoginResponseDto> userAdd(@RequestBody UserJoinRequestDto userJoinRequestDto) {
        return userService.join(userJoinRequestDto);
    }

    @GetMapping("/check")
    public void check(@RequestHeader HttpHeaders header) {
        log.info("checkController");
        log.info("header : {}", header.getFirst("Authorization"));
        return ;
    }
}
