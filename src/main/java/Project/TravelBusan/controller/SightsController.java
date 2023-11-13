package Project.TravelBusan.controller;

import Project.TravelBusan.request.Sights.SightsRequestDto;
import Project.TravelBusan.response.ResponseDto;
import Project.TravelBusan.service.SightsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor // 생성자 자동주입
@RequestMapping("/sights")
public class SightsController {

    private final SightsService sightsService;

    @GetMapping
    public ResponseDto<?> getBoards() {
        return sightsService.sightsList();
    }
}
