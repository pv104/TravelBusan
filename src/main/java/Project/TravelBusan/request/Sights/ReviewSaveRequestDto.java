package Project.TravelBusan.request.Sights;


import Project.TravelBusan.domain.Review;
import Project.TravelBusan.domain.Sights;
import Project.TravelBusan.response.Sights.SightsListResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewSaveRequestDto {
    private String title;
    private String comment;
    private int rating;
}
