package Project.TravelBusan.response.Sights;

import Project.TravelBusan.domain.Review;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ReviewResponseDto {

    private Long reviewId;
    private Long sightsId;
    private String writer;
    private String comment;
    private int rating;

    @JsonFormat(pattern = "yyyy.MM.dd hh:mm")
    private Timestamp creDate;

    public ReviewResponseDto(Review review) {
        reviewId = review.getId();
        sightsId = review.getSights().getId();
        writer = review.getUser().getNickname();
        comment = review.getComment();
        rating = review.getRating();
        creDate = review.getCreDate();
    }
}
