package Project.TravelBusan.service;


import Project.TravelBusan.domain.Member;
import Project.TravelBusan.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
    public Long join(Member member) {
        memberRepository.save(member);
        return member.getId();
    }

    public Member findById(Long id){
        Member member = memberRepository.findById(id).orElseThrow(() ->
                new IllegalStateException("없는 아이디 입니다"));
        return member;
    }
}
