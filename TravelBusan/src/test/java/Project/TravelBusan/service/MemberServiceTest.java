package Project.TravelBusan.service;

import Project.TravelBusan.domain.Member;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class MemberServiceTest {
    @Autowired MemberService memberService;

    @Test
    @Transactional
    public void 회원가입_(){
        //given
        Member member = Member.builder()
                .username("test")
                .password("123")
                .email("aaa")
                .build();

        //when
        Long memberId = memberService.join(member);

        //then
        Member result = memberService.findById(memberId);
        Assertions.assertEquals(member, result);
    }
}