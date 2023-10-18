package Project.TravelBusan.controller;

import Project.TravelBusan.request.MemberJoinRequestDto;
import Project.TravelBusan.request.MemberLoginRequestDto;
import Project.TravelBusan.request.MemberModifyRequestDto;
import Project.TravelBusan.response.MemberListResponseDto;
import Project.TravelBusan.response.MemberLoginResponseDto;
import Project.TravelBusan.response.ResponseDto;
import Project.TravelBusan.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor // 생성자 자동주입
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseDto<MemberLoginResponseDto> memberAdd(@RequestBody MemberJoinRequestDto memberJoinRequestDto) {
        return memberService.join(memberJoinRequestDto);
    }

    @GetMapping
    public ResponseDto<List<MemberListResponseDto>>  memberList(){
        return memberService.findAllMembers();
    }

    @GetMapping("/{member-id}")
    public ResponseDto<MemberListResponseDto> memberDetails(@PathVariable("member-id") Long memberId){
        return memberService.findById(memberId);
    }

    @PatchMapping("/{member-id}")
    public ResponseDto<MemberListResponseDto> memberModify(@PathVariable("member-id") Long memberId, @RequestBody MemberModifyRequestDto memberModifyRequestDto){
        return memberService.updateMemberById(memberId, memberModifyRequestDto);
    }

    @DeleteMapping("/{member-id}")
    public ResponseDto<Void> memberRemove(@PathVariable("member-id") Long memberId){
        return memberService.deleteById(memberId);
    }

    @PostMapping("/login")
    public ResponseDto<MemberLoginResponseDto> memberLogin(@RequestBody MemberLoginRequestDto memberLoginRequestDto){
        return memberService.login(memberLoginRequestDto);
    }
}
