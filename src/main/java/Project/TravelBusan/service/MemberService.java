package Project.TravelBusan.service;


import Project.TravelBusan.domain.Member;
import Project.TravelBusan.repository.MemberRepository;
import Project.TravelBusan.request.MemberJoinRequestDto;
import Project.TravelBusan.request.MemberLoginRequestDto;
import Project.TravelBusan.request.MemberModifyRequestDto;
import Project.TravelBusan.response.MemberListResponseDto;
import Project.TravelBusan.response.MemberLoginResponseDto;
import Project.TravelBusan.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원 가입
     */
    @Transactional
    public ResponseDto<MemberLoginResponseDto> join(MemberJoinRequestDto memberJoinRequestDto) {
        if (isPresentUsername(memberJoinRequestDto.getUsername())) {
            throw new IllegalStateException("이미 존재하는 아이디 입니다");
        }

        if(!memberJoinRequestDto.getPassword().equals(memberJoinRequestDto.getPasswordCheck())){
            throw new IllegalStateException("비빌번호와 비밀번호 확인이 일치하지 않습니다");
        }

        Member member = Member.builder()
                .nickname(memberJoinRequestDto.getNickname())
                .username(memberJoinRequestDto.getUsername())
                .password(passwordEncoder.encode(memberJoinRequestDto.getPassword()))
                .email(memberJoinRequestDto.getEmail())
                .build();

        memberRepository.save(member);

        return ResponseDto.success("회원가입 성공",
                MemberLoginResponseDto.builder()
                        .id(member.getId())
                        .nickname(member.getNickname())
                        .build()
        );
    }

    public boolean isPresentUsername(String username) {
        return memberRepository.findByUsername(username).isPresent();
    }


    /**
     * 로그인
     */
    public ResponseDto<MemberLoginResponseDto> login(MemberLoginRequestDto memberLoginRequestDto){
        Member member = memberRepository.findByUsername(memberLoginRequestDto.getUsername()).orElseThrow(() ->
                new IllegalStateException("존재하지 않는 아이디 입니다"));

        if(!passwordEncoder.matches(memberLoginRequestDto.getPassword(), member.getPassword())){
            throw new IllegalStateException("패스워드가 일치하지 않습니다");
        }

        return ResponseDto.success("로그인 성공",
                MemberLoginResponseDto.builder()
                        .id(member.getId())
                        .nickname(member.getNickname())
                        .build()
        );
    }

    /**
     * 회원 상세 조회
     */
    public ResponseDto<MemberListResponseDto> findById(Long memberId) {
        Member member = memberRepository.findByIdOrElseThrow(memberId);

        return ResponseDto.success("회원 조회 성공",
                MemberListResponseDto.builder()
                        .id(member.getId())
                        .username(member.getUsername())
                        .nickname(member.getNickname())
                        .email(member.getEmail())
                        .creDate(member.getCreDate())
                        .build()
        );
    }

    /**
     * 회원 전체 조회
     */
    public ResponseDto<List<MemberListResponseDto>> findAllMembers() {
        List<Member> members = memberRepository.findAll();
        List<MemberListResponseDto> memberListResponseDto = new ArrayList<>();

        for (Member member : members) {
            MemberListResponseDto dto = MemberListResponseDto.builder()
                    .id(member.getId())
                    .username(member.getUsername())
                    .nickname(member.getNickname())
                    .email(member.getEmail())
                    .creDate(member.getCreDate())
                    .build();
            memberListResponseDto.add(dto);
        }
        return ResponseDto.success("전체 회원 조회 성공", memberListResponseDto);
    }


    /**
     * 회원 수정
     */
    @Transactional
    public ResponseDto<MemberListResponseDto> updateMemberById(Long memberId, MemberModifyRequestDto memberModifyRequestDto) {
        Member member = memberRepository.findByIdOrElseThrow(memberId);

        if(!memberModifyRequestDto.getPassword().equals(memberModifyRequestDto.getPasswordCheck())){
            throw new IllegalStateException("새 비밀번호와 비밀번호 확인이 일치하지 않습니다.");

        }

        member.modifyMember(passwordEncoder.encode(memberModifyRequestDto.getPassword()), memberModifyRequestDto.getEmail(), memberModifyRequestDto.getNickname());

        memberRepository.save(member);

        return ResponseDto.success("회원 수정 성공",
                MemberListResponseDto.builder()
                        .id(member.getId())
                        .username(member.getUsername())
                        .nickname(member.getNickname())
                        .email(member.getEmail())
                        .creDate(member.getCreDate())
                        .build()
                );
    }


    /**
     * 회원 삭제
     */
    @Transactional
    public ResponseDto<Void> deleteById(Long memberId) {
        Member member = memberRepository.findByIdOrElseThrow(memberId);
        memberRepository.deleteById(member.getId());
        return ResponseDto.success("회원 삭제 성공",null);
    }
}
