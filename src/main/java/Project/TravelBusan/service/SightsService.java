package Project.TravelBusan.service;

import Project.TravelBusan.domain.Member;
import Project.TravelBusan.domain.Sights;
import Project.TravelBusan.repository.MemberRepository;
import Project.TravelBusan.repository.SightsRepository;
import Project.TravelBusan.request.SightsRequestDto;
import Project.TravelBusan.response.ResponseDto;
import Project.TravelBusan.response.SightsResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SightsService {

    private final SightsRepository sightsRepository;
    private final MemberRepository memberRepository;

    /**
     * 명소 등록
     */
    @Transactional
    public ResponseDto<?> saveSights(SightsRequestDto sightsRequestDto,Long memberId) {
        Member member = memberRepository.findByIdOrElseThrow(memberId);

        Sights sights = Sights.builder()
                .name(member.getNickname())
                .info(sightsRequestDto.getInfo())
                .addr(sightsRequestDto.getAddr())
                .mapx(sightsRequestDto.getMapx())
                .mapy(sightsRequestDto.getMapy())
                .img(sightsRequestDto.getImg())
                .city(sightsRequestDto.getCity())
                .type(sightsRequestDto.getType())
                .member(member)
                .build();

        sightsRepository.save(sights);

        return ResponseDto.success("성공",
                SightsResponseDto.builder()
                        .id(sights.getId())
                        .name(sights.getName())
                        .build()
        );
    }

    public ResponseDto<?> sightsList() {
        List<Sights> boards = sightsRepository.findAll();
        List<SightsResponseDto> boardDto = boards.stream()
                .map(SightsResponseDto::toDto)
                .collect(Collectors.toList());
        return ResponseDto.success("명소 전체 조회", boardDto);
    }


    // 지역별 검색
    public ResponseDto<?> searchList() {
        List<Sights> boards = sightsRepository.findAll();
        List<SightsResponseDto> boardDto = boards.stream()
                .map(SightsResponseDto::toDto)
                .collect(Collectors.toList());
        return ResponseDto.success("명소 지역 별 조회", boardDto);
    }
}
