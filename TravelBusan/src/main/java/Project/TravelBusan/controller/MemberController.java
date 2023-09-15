package Project.TravelBusan.controller;

import Project.TravelBusan.domain.Member;
import Project.TravelBusan.response.ResponseDto;
import Project.TravelBusan.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor // 생성자 자동주입
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseDto<?> createMember(@RequestBody Member member) {
        log.info("controller!!");
        memberService.join(member);
        return ResponseDto.success("성공",null);
    }

    @GetMapping
    public ResponseDto<?> hello(){
        log.info("hello!");
        return ResponseDto.success("hello",null);
    }
}
