package Project.TravelBusan.service;


import Project.TravelBusan.domain.Authority;
import Project.TravelBusan.domain.User;
import Project.TravelBusan.exception.DuplicateUserException;
import Project.TravelBusan.exception.NotFoundUserException;
import Project.TravelBusan.repository.UserRepository;
import Project.TravelBusan.request.UserJoinRequestDto;
import Project.TravelBusan.request.UserLoginRequestDto;
import Project.TravelBusan.request.UserModifyRequestDto;
import Project.TravelBusan.response.UserListResponseDto;
import Project.TravelBusan.response.UserLoginResponseDto;
import Project.TravelBusan.response.ResponseDto;
import Project.TravelBusan.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    /**
     * 회원 가입
     */
    @Transactional
    public ResponseDto<UserLoginResponseDto> join(UserJoinRequestDto userJoinRequestDto) {
        /*if (isPresentUsername(userJoinRequestDto.getUsername())) {
            throw new DuplicateUserException("이미 존재하는 아이디 입니다");
        }

        if(!userJoinRequestDto.getPassword().equals(userJoinRequestDto.getPasswordCheck())){
            logger.info(" original : {}",userJoinRequestDto.getPassword());
            logger.info(" Check :  {}", userJoinRequestDto.getPasswordCheck());
            throw new IllegalStateException("비빌번호와 비밀번호 확인이 일치하지 않습니다");
        }*/

            Authority authority = Authority.builder()
                    .authorityName("ROLE_USER")
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




    public boolean isPresentUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }


    /**
     * 로그인
     */
    public ResponseDto<UserLoginResponseDto> login(UserLoginRequestDto userLoginRequestDto){
        User user = userRepository.findByUsername(userLoginRequestDto.getUsername()).orElseThrow(() ->
                new DuplicateUserException("존재하지 않는 아이디 입니다"));

        if(!passwordEncoder.matches(userLoginRequestDto.getPassword(), user.getPassword())){
            throw new IllegalStateException("패스워드가 일치하지 않습니다");
        }

        return ResponseDto.success("로그인 성공",
                UserLoginResponseDto.builder()
                        .id(user.getId())
                        .nickname(user.getNickname())
                        .build()
        );
    }

    /**
     * 회원 상세 조회
     */
    public ResponseDto<UserListResponseDto> findById(Long userId) {
        User user = userRepository.findByIdOrElseThrow(userId);

        return ResponseDto.success("회원 조회 성공",
                UserListResponseDto.builder()
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
    public ResponseDto<List<UserListResponseDto>> findAllUsers() {
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
    public ResponseDto<UserListResponseDto> updateUserById(Long userId, UserModifyRequestDto userModifyRequestDto) {
        User user = userRepository.findByIdOrElseThrow(userId);

        if(!userModifyRequestDto.getPassword().equals(userModifyRequestDto.getPasswordCheck())){
            throw new IllegalStateException("새 비밀번호와 비밀번호 확인이 일치하지 않습니다.");

        }

        user.modifyUser(passwordEncoder.encode(userModifyRequestDto.getPassword()), userModifyRequestDto.getEmail(), userModifyRequestDto.getNickname());

        userRepository.save(user);

        return ResponseDto.success("회원 수정 성공",
                UserListResponseDto.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .nickname(user.getNickname())
                        .email(user.getEmail())
                        .creDate(user.getCreDate())
                        .build()
                );
    }


    /**
     * 회원 삭제
     */
    @Transactional
    public ResponseDto<Void> deleteById(Long userId) {
        User user = userRepository.findByIdOrElseThrow(userId);
        userRepository.deleteById(user.getId());
        return ResponseDto.success("회원 삭제 성공",null);
    }
    @Transactional(readOnly = true)
    public UserJoinRequestDto getUserWithAuthorities(String username) {
        return UserJoinRequestDto.from(userRepository.findOneWithAuthoritiesByUsername(username).orElse(null));
    }
    @Transactional(readOnly = true)
    public UserLoginRequestDto getMyUserWithAuthorities() {
        return UserLoginRequestDto.from(
                SecurityUtil.getCurrentUsername()
                        .flatMap(userRepository::findOneWithAuthoritiesByUsername)
                        .orElseThrow(() -> new NotFoundUserException("User not found"))
        );
    }
}

