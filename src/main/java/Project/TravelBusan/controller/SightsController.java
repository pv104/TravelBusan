package Project.TravelBusan.controller;

import Project.TravelBusan.request.Sights.SightsRequestDto;
import Project.TravelBusan.response.ResponseDto;
import Project.TravelBusan.response.Sights.SightsDetailResponseDto;
import Project.TravelBusan.response.Sights.SightsResponseDto;
import Project.TravelBusan.service.SightsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor // 생성자 자동주입
@RequestMapping("/sights")
public class SightsController {

    private final SightsService sightsService;

    @GetMapping
    public ResponseDto<List<SightsResponseDto>> listSights() {
        return sightsService.sightsList();
    }

    @GetMapping("/{sights-id}")
    public ResponseDto<SightsDetailResponseDto> detailSights(@PathVariable("sights-id") Long sightsId) {
        return sightsService.sightsDetail(sightsId);
    }

    @GetMapping("/search")
    public ResponseDto<List<SightsResponseDto>> searchList(@RequestParam String city) {
        return sightsService.searchListByCity(city);
    }
}
