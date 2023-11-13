package Project.TravelBusan.service;

import Project.TravelBusan.domain.Sights;
import Project.TravelBusan.repository.UserRepository;
import Project.TravelBusan.repository.SightsRepository;
import Project.TravelBusan.response.ResponseDto;
import Project.TravelBusan.response.Sights.SightsDetailResponseDto;
import Project.TravelBusan.response.Sights.SightsListResponseDto;
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
    private final UserRepository userRepository;

    /**
     * 명소 조회
     */
    public ResponseDto<List<SightsListResponseDto>> sightsList() {
        List<Sights> boards = sightsRepository.findAll();
        List<SightsListResponseDto> boardDto = boards.stream()
                .map(SightsListResponseDto::toDto)
                .collect(Collectors.toList());
        return ResponseDto.success("명소 전체 조회", boardDto);
    }

    /**
     * 명소 상세 조회
     */
    public ResponseDto<SightsDetailResponseDto> sightsDetail(Long sightsId) {
        Sights sights = sightsRepository.findById(sightsId).orElseThrow(() ->
                new IllegalStateException("존재하지 않는 명소입니다"));
        return ResponseDto.success("명소 상세 조회", SightsDetailResponseDto.toDto(sights));
    }


    /**
     * 구군별 조회
     */
    public ResponseDto<List<SightsListResponseDto>> searchListByCity(String city) {
        List<Sights> boards = sightsRepository.findByCity(city);

        List<SightsListResponseDto> boardDto = boards.stream()
                .map(SightsListResponseDto::toDto)
                .collect(Collectors.toList());

        return ResponseDto.success(" 구군별 명소 조회", boardDto);
    }
}
