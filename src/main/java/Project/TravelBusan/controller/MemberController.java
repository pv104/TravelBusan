package Project.TravelBusan.controller;

import Project.TravelBusan.request.MemberJoinRequestDto;
import Project.TravelBusan.request.MemberModifyRequestDto;
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
    public ResponseDto<?> createMember(@RequestBody MemberJoinRequestDto memberJoinRequestDto) {
        return memberService.join(memberJoinRequestDto);
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
    public ResponseDto<?> updateMember(@PathVariable Long memberId, @RequestBody MemberModifyRequestDto memberModifyRequestDto){
        return memberService.updateMemberById(memberId, memberModifyRequestDto);
    }

    @DeleteMapping("/{memberId}")
    public ResponseDto<?> deleteMember(@PathVariable Long memberId){
        return memberService.deleteById(memberId);
    }

    @PostMapping("/login")
    public ResponseDto<?> memberLogin(@RequestBody MemberJoinRequestDto memberJoinRequestDto){
        return memberService.login(memberJoinRequestDto);
    }
}
