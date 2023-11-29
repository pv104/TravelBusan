package Project.TravelBusan.service;


import Project.TravelBusan.domain.Authority;
import Project.TravelBusan.domain.Blog;
import Project.TravelBusan.domain.User;
import Project.TravelBusan.exception.DuplicateUserException;
import Project.TravelBusan.exception.NotFoundUserException;
import Project.TravelBusan.jwt.JwtFilter;
import Project.TravelBusan.jwt.TokenProvider;
import Project.TravelBusan.repository.BlogRepository;
import Project.TravelBusan.repository.UserRepository;
import Project.TravelBusan.request.TokenDto;
import Project.TravelBusan.request.User.UserJoinRequestDto;
import Project.TravelBusan.request.User.UserLoginRequestDto;
import Project.TravelBusan.request.User.UserModifyRequestDto;
import Project.TravelBusan.response.Blog.BlogSimplelResponseDto;
import Project.TravelBusan.response.User.*;
import Project.TravelBusan.response.ResponseDto;
import Project.TravelBusan.util.SecurityUtil;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final BlogRepository blogRepository;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;


    /**
     * 회원 가입
     */
    @Transactional
    public ResponseDto<UserLoginResponseDto> join(UserJoinRequestDto userJoinRequestDto) {
        if(userRepository.findByUsername(userJoinRequestDto.getUsername()).isPresent()){
            throw new DuplicateUserException("이미 존재하는 아이디 입니다");
        }

        Authority authority = Authority.builder()
                .authorityName("ROLE_ADMIN")
                .build();

        User user = User.builder()
                .nickname(userJoinRequestDto.getNickname())
                .username(userJoinRequestDto.getUsername())
                .password(passwordEncoder.encode(userJoinRequestDto.getPassword()))
                .email(userJoinRequestDto.getEmail())
                .authorities((Collections.singleton(authority)))
                .activated(true)
                .build();

        userRepository.save(user);

        return ResponseDto.success("회원가입 성공",
                UserLoginResponseDto.builder()
                        .id(user.getId())
                        .nickname(user.getNickname())
                        .build()
        );
    }

    /**
     * 로그인
     */
    public ResponseEntity<ResponseDto<TokenDto>> login(UserLoginRequestDto userLoginRequestDto){
        User user = userRepository.findByUsername(userLoginRequestDto.getUsername()).orElseThrow(() ->
                new NotFoundUserException("존재하지 않는 아이디 입니다"));

        if(!passwordEncoder.matches(userLoginRequestDto.getPassword(), user.getPassword())){
            throw new ValidationException("패스워드가 일치하지 않습니다");
        }

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userLoginRequestDto.getUsername(), userLoginRequestDto.getPassword()); // 사용자 아이디와 비밀번호를 가지고 인증 토큰을 생성
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken); // 사용자가 제출한 사용자 아이디와 비밀번호를 사용하여 인증을 시도
        SecurityContextHolder.getContext().setAuthentication(authentication); // 로그인 성공시 SecurityContextHolder 에 사용자의 정보를 보관

        String jwt = tokenProvider.createToken(authentication); //JWT (JSON Web Token)를 생성하는 메서드

        HttpHeaders httpHeaders = new HttpHeaders(); //  HTTP 헤더 정보를 저장하고 관리하는 클래스
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt); //  HTTP 응답 헤더에 JWT(Access Token) 추가

        return new ResponseEntity<>(ResponseDto.success("로그인 성공", new TokenDto(jwt)), httpHeaders, HttpStatus.OK);
    }

    /**
     * 회원 상세 조회
     */
    public ResponseDto<UserDetailResponseDto> detailUser() {
        User user = userRepository.findByIdOrElseThrow(getMyUserWithAuthorities().getId());

        return ResponseDto.success("회원 조회 성공",
                UserDetailResponseDto.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .nickname(user.getNickname())
                        .email(user.getEmail())
                        .creDate(user.getCreDate())
                        .build()
        );
    }

    /**
     * 회원 전체 조회
     */
    public ResponseDto<List<UserListResponseDto>> listUser() {
        List<User> users = userRepository.findAll();
        List<UserListResponseDto> userListResponseDto = new ArrayList<>();

        for (User user : users) {
            UserListResponseDto dto = UserListResponseDto.builder()
                    .id(user.getId())
                    .username(user.getUsername())
                    .nickname(user.getNickname())
                    .email(user.getEmail())
                    .creDate(user.getCreDate())
                    .build();
            userListResponseDto.add(dto);
        }
        return ResponseDto.success("전체 회원 조회 성공", userListResponseDto);
    }


    /**
     * 회원 수정
     */
    @Transactional
    public ResponseDto<UserModifyResponseDto> modifyUser(UserModifyRequestDto userModifyRequestDto) {
        User user = userRepository.findByIdOrElseThrow(getMyUserWithAuthorities().getId());
        user.modifyUser(passwordEncoder.encode(userModifyRequestDto.getPassword()), userModifyRequestDto.getEmail(), userModifyRequestDto.getNickname());

        userRepository.save(user);

        return ResponseDto.success("회원 수정 성공",
                UserModifyResponseDto.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .nickname(user.getNickname())
                        .email(user.getEmail())
                        .creDate(user.getCreDate())
                        .build()
                );
    }


    /**
     * 회원 탈퇴
     */
    @Transactional
    public ResponseDto<Void> removeUser() {
        userRepository.deleteById(getMyUserWithAuthorities().getId());
        return ResponseDto.success("회원 삭제",null);
    }

/*
    public UserLoginRequestDto getUserWithAuthorities(String username) {
        return UserLoginRequestDto.from(userRepository.findOneWithAuthoritiesByUsername(username).orElse(null));
    }
*/

    public UserAuthoritiesResponseDto getMyUserWithAuthorities() {
        return UserAuthoritiesResponseDto.from(
                SecurityUtil.getCurrentUsername()
                        .flatMap(userRepository::findOneWithAuthoritiesByUsername)
                        .orElseThrow(() -> new NotFoundUserException("Member not found"))
        );
    }

    /**
     * 내가 쓴 블로그 조회
     */
    public ResponseDto<List<BlogSimplelResponseDto>> getMyBlogs() {
        User user = userRepository.findByIdOrElseThrow(getMyUserWithAuthorities().getId());
        List<Blog> blog = blogRepository.findByUserId(user.getId());

        List<BlogSimplelResponseDto> myBlogDto = blog.stream()
                .map(BlogSimplelResponseDto::new)
                .collect(Collectors.toList());

        return ResponseDto.success("내가 쓴 블로그", myBlogDto);
    }
}
