package Project.TravelBusan.controller;

import Project.TravelBusan.domain.User;
import Project.TravelBusan.jwt.JwtFilter;
import Project.TravelBusan.jwt.TokenProvider;
import Project.TravelBusan.request.TokenDto;
import Project.TravelBusan.request.User.UserJoinRequestDto;
import Project.TravelBusan.request.User.UserLoginRequestDto;
import Project.TravelBusan.request.User.UserModifyRequestDto;
import Project.TravelBusan.response.Blog.BlogSimplelResponseDto;
import Project.TravelBusan.response.User.*;
import Project.TravelBusan.response.ResponseDto;
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
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    @PostMapping
    public ResponseDto<UserLoginResponseDto> userAdd(@RequestBody UserJoinRequestDto userJoinRequestDto) {
        return userService.join(userJoinRequestDto);
    }

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

/*    @GetMapping("/user")
    public ResponseEntity<UserLoginRequestDto> getMyUserInfo() {
        return ResponseEntity.ok(userService.getMyUserWithAuthorities());
    }*/

    @PostMapping("/login")
    public ResponseEntity<ResponseDto<TokenDto>> userLogin(@Valid @RequestBody UserLoginRequestDto userLoginRequestDto) {
        return userService.login(userLoginRequestDto);
    }


    @GetMapping("/check")
    public UserAuthoritiesResponseDto check(@RequestHeader HttpHeaders header) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        SecurityContextHolder.clearContext();
        log.info("getUserAuthorities : {}",authentication.getName());
        log.info("header : {}", header.getFirst("Authorization"));
        return userService.getMyUserWithAuthorities();
    }

    @GetMapping("my-blogs")
    public ResponseDto<List<BlogSimplelResponseDto>> myBlog(){
        return userService.getMyBlogs();
    }


}
