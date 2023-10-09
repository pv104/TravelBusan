package Project.TravelBusan.TravelBusan.service;

import Project.TravelBusan.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class MemberServiceTest {
    @Autowired
    MemberService memberService;

/*    @Test
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
    }*/
}