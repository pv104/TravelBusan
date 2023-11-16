package Project.TravelBusan.service;

import Project.TravelBusan.domain.Review;
import Project.TravelBusan.domain.Sights;
import Project.TravelBusan.domain.User;
import Project.TravelBusan.repository.ReviewRepository;
import Project.TravelBusan.repository.SightsRepository;
import Project.TravelBusan.repository.UserRepository;
import Project.TravelBusan.request.Sights.ReviewSaveRequestDto;
import Project.TravelBusan.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final SightsRepository sightsRepository;
    private final UserRepository userRepository;
    private final UserService userService;


    /**
     * 리뷰 작성
     */
    @Transactional
    public ResponseDto<ResponseDto> addReview(Long sightsId, ReviewSaveRequestDto reviewSaveRequestDto) {
        User user = userRepository.findByIdOrElseThrow(userService.getMyUserWithAuthorities().getId());
        Sights sights = sightsRepository.findByIdOrElseThrow(sightsId);


        Review review = Review.builder()
                .comment(reviewSaveRequestDto.getComment())
                .title(reviewSaveRequestDto.getTitle())
                .rating(reviewSaveRequestDto.getRating())
                .sights(sights)
                .user(user)
                .build();

        reviewRepository.save(review);

        return ResponseDto.success("리뷰 작성",null);
    }

    /**
     * 리뷰 삭제
     */
    @Transactional
    public ResponseDto<Void> reviewRemove(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(() ->
                new IllegalStateException("존재하지 않는 리뷰 입니다"));

        User user = userRepository.findByIdOrElseThrow(userService.getMyUserWithAuthorities().getId());

        if (!review.getUser().getId().equals(user.getId())) {
            throw new IllegalStateException("해당 리뷰 작성자가 아닙니다.");
        }

        reviewRepository.delete(review);
        return ResponseDto.success("리뷰 삭제",null);
    }
}
