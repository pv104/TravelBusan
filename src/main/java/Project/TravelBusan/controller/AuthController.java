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
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PostMapping("/authenticate")
    public ResponseEntity<TokenDto> authorize(@Valid @RequestBody UserJoinRequestDto userJoinRequestDto) {

        logger.info("궁금");
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userJoinRequestDto.getUsername(), userJoinRequestDto.getPassword());
        logger.info("{} {}",authenticationToken.getCredentials(), authenticationToken.getPrincipal());
        logger.info(authenticationManagerBuilder.getObject().authenticate(authenticationToken).toString());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        logger.info("궁금2");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        logger.info("궁금3");
        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
    }


}