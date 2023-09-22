package Project.TravelBusan.service;


import Project.TravelBusan.domain.Member;
import Project.TravelBusan.repository.MemberRepository;
import Project.TravelBusan.request.MemberRequestDto;
import Project.TravelBusan.response.MemberResponseDto;
import Project.TravelBusan.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    /**
     * 회원 가입
     */
    @Transactional
    public ResponseDto<?> join(MemberRequestDto memberRequestDto) {
        if (isPresentUsername(memberRequestDto.getUsername())) {
            throw new IllegalStateException("이미 존재하는 아이디 입니다");
        }

        Member member = Member.builder()
                .username(memberRequestDto.getUsername())
                .password(memberRequestDto.getPassword())
                .email(memberRequestDto.getEmail())
                .build();

        memberRepository.save(member);

        return ResponseDto.success("회원가입 성공",
                MemberResponseDto.builder()
                        .id(member.getId())
                        .username(member.getUsername())
                        .email(member.getEmail())
                        .build()
        );
    }

    public boolean isPresentUsername(String username) {
        return memberRepository.findByUsername(username).isPresent();
    }


    /**
     * 회원 조회
     */
    public ResponseDto<?> findById(Long memberId) {
        Member member = memberRepository.findByIdOrElseThrow(memberId);

        return ResponseDto.success("멤버 조회 성공",
                MemberResponseDto.builder()
                        .id(member.getId())
                        .username(member.getUsername())
                        .email(member.getEmail())
                        .build()
        );
    }

    public ResponseDto<?> findAllMembers() {
        List<Member> members = memberRepository.findAll();
        List<MemberResponseDto> memberResponseDto = new ArrayList<>();

        for (Member member : members) {
            MemberResponseDto dto = MemberResponseDto.builder()
                    .id(member.getId())
                    .username(member.getUsername())
                    .email(member.getEmail())
                    .build();
            memberResponseDto.add(dto);
        }
        return ResponseDto.success("전체 회원 조회 성공", memberResponseDto);
    }


    /**
     * 회원 수정
     */
    @Transactional
    public ResponseDto<?> updateMemberById(Long memberId, MemberRequestDto memberRequestDto) {
        Member member = memberRepository.findByIdOrElseThrow(memberId);

        member.modify(memberRequestDto.getPassword(), memberRequestDto.getEmail());

        memberRepository.save(member);

        return ResponseDto.success("회원 수정 성공",
                MemberResponseDto.builder()
                        .id(member.getId())
                        .username(member.getUsername())
                        .email(member.getEmail())
                        .build()
                );
    }


    /**
     * 회원 삭제
     */
    @Transactional
    public ResponseDto<?> deleteById(Long memberId) {
        Member member = memberRepository.findByIdOrElseThrow(memberId);
        memberRepository.deleteById(member.getId());
        return ResponseDto.success("회원 삭제 성공",null);
    }
}
