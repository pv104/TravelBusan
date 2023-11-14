package Project.TravelBusan.response.Sights;

import Project.TravelBusan.domain.Review;
import lombok.Data;

@Data
public class ReviewResponseDto {

    private Long reviewId;
    private Long sightsId;
    private String writer;
    private String comment;
    private int rating;


    public ReviewResponseDto(Review review) {
        reviewId = review.getId();
        sightsId = review.getSights().getId();
        writer = review.getUser().getNickname();
        comment = review.getComment();
        rating = review.getRating();
    }
}
