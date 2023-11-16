package Project.TravelBusan.controller;

import Project.TravelBusan.request.Sights.ReviewSaveRequestDto;
import Project.TravelBusan.response.ResponseDto;
import Project.TravelBusan.response.Sights.SightsDetailResponseDto;
import Project.TravelBusan.response.Sights.SightsListResponseDto;
import Project.TravelBusan.service.ReviewService;
import Project.TravelBusan.service.SightsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/sights")
public class SightsController {

    private final SightsService sightsService;
    private final ReviewService reviewService;

    @GetMapping
    public ResponseDto<List<SightsListResponseDto>> listSights() {
        return sightsService.sightsList();
    }

    @GetMapping("/{sights-id}")
    public ResponseDto<SightsDetailResponseDto> detailSights(@PathVariable("sights-id") Long sightsId) {
        return sightsService.sightsDetail(sightsId);
    }

    @GetMapping("/search")
    public ResponseDto<List<SightsListResponseDto>> searchList(@RequestParam String city) {
        return sightsService.searchListByCity(city);
    }

    @PostMapping("/{sights-id}/review")
    public ResponseDto<ResponseDto> reviewAdd(@PathVariable("sights-id") Long sightsId, @RequestBody ReviewSaveRequestDto reviewSaveRequestDto) {
        return reviewService.addReview(sightsId, reviewSaveRequestDto);
    }

    @DeleteMapping("/{sights-id}/review/{review-id}")
    public ResponseDto<Void> reviewRemove(@PathVariable("review-id") Long reviewId, @PathVariable("sights-id") String sightsId) {
        return reviewService.reviewRemove(reviewId);
    }

}
