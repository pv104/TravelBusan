package Project.TravelBusan.service;

import Project.TravelBusan.domain.User;
import Project.TravelBusan.domain.Sights;
import Project.TravelBusan.repository.UserRepository;
import Project.TravelBusan.repository.SightsRepository;
import Project.TravelBusan.request.Sights.SightsRequestDto;
import Project.TravelBusan.response.ResponseDto;
import Project.TravelBusan.response.Sights.SightsResponseDto;
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
