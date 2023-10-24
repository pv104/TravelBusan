package Project.TravelBusan.controller;

import Project.TravelBusan.exception.NotFoundUserException;
import Project.TravelBusan.repository.UserRepository;
import Project.TravelBusan.request.UserJoinRequestDto;
import Project.TravelBusan.request.UserLoginRequestDto;
import Project.TravelBusan.request.UserModifyRequestDto;
import Project.TravelBusan.response.UserListResponseDto;
import Project.TravelBusan.response.UserLoginResponseDto;
import Project.TravelBusan.response.ResponseDto;
import Project.TravelBusan.service.UserService;
import Project.TravelBusan.util.SecurityUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor // 생성자 자동주입
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping
    public ResponseDto<List<UserListResponseDto>>  userList(){
        return userService.findAllUsers();
    }

    @GetMapping("/{user-id}")
    public ResponseDto<UserListResponseDto> userDetails(@PathVariable("user-id") Long userId){
        return userService.findById(userId);
    }

    @PatchMapping("/{user-id}")
    public ResponseDto<UserListResponseDto> userModify(@PathVariable("user-id") Long userId, @RequestBody UserModifyRequestDto userModifyRequestDto){
        return userService.updateUserById(userId, userModifyRequestDto);
    }

    @DeleteMapping("/{user-id}")
    public ResponseDto<Void> userRemove(@PathVariable("user-id") Long userId){
        return userService.deleteById(userId);
    }

    @PostMapping("/login")
    public ResponseDto<UserLoginResponseDto> userLogin(@RequestBody UserLoginRequestDto userLoginRequestDto){
        return userService.login(userLoginRequestDto);
    }

    /*@PostMapping("/test-redirect")
    public void testRedirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/api/user");
    }*/

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<UserLoginRequestDto> getMyUserInfo(HttpServletRequest request) {
        return ResponseEntity.ok(userService.getMyUserWithAuthorities());
    }

    @GetMapping("/user/{username}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<UserJoinRequestDto> getUserInfo(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserWithAuthorities(username));
    }
}
