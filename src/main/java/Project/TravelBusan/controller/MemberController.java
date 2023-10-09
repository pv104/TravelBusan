package Project.TravelBusan.controller;

/*
import Project.TravelBusan.request.MemberRequestDto;
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
    public ResponseDto<?> createMember(@RequestBody MemberRequestDto memberRequestDto) {
        return memberService.join(memberRequestDto);
    }

    @GetMapping
    public ResponseDto<?> memberList(){
        return memberService.findAllMembers();
    }

    @GetMapping("/{memberId}")
    public ResponseDto<?> findMember(@PathVariable Long memberId){
        return memberService.findById(memberId);
    }

    @PatchMapping("/{memberId}")
    public ResponseDto<?> updateMember(@PathVariable Long memberId,@RequestBody MemberRequestDto memberRequestDto){
        return memberService.updateMemberById(memberId,memberRequestDto);
    }

    @DeleteMapping("/{memberId}")
    public ResponseDto<?> deleteMember(@PathVariable Long memberId){
        return memberService.deleteById(memberId);
    }

    @PostMapping("/login")
    public ResponseDto<?> memberLogin(@RequestBody MemberRequestDto memberRequestDto){
        return memberService.login(memberRequestDto);
    }
}
*/