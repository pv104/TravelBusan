package Project.TravelBusan.controller;

import Project.TravelBusan.repository.UserRepository;
import Project.TravelBusan.request.UserJoinRequestDto;
import Project.TravelBusan.request.UserLoginRequestDto;
import Project.TravelBusan.request.TokenDto;
import Project.TravelBusan.jwt.JwtFilter;
import Project.TravelBusan.jwt.TokenProvider;
import Project.TravelBusan.response.ResponseDto;
import Project.TravelBusan.response.UserLoginResponseDto;
import Project.TravelBusan.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class AuthController {
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final UserService userService;

    private final UserRepository userRepository;
    public AuthController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, UserService userService, UserRepository userRepository) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> authorize(@Valid @RequestBody UserLoginRequestDto userLoginRequestDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userLoginRequestDto.getUsername(), userLoginRequestDto.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);


        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
    }
    @PostMapping("/signup")
    public ResponseDto<UserLoginResponseDto> userAdd(@RequestBody UserJoinRequestDto userJoinRequestDto) {

        return userService.join(userJoinRequestDto);
    }

}