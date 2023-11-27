package Project.TravelBusan.response.Sights;

import Project.TravelBusan.domain.Review;
import Project.TravelBusan.domain.Sights;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SightsDetailResponseDto {
    private Long id;
    private String name;
    private String title;
    private String info;
    private String addr;
    private String mapx;
    private String mapy;
    private String img;
    private String city;
    private String homepage;
    private String number;
    private String openDate;
    private String trafficReport;
    private String like;
    private double rate;
    private List<ReviewResponseDto> reviews; // 리뷰



    public SightsDetailResponseDto(Sights sights) {
        id = sights.getId();
        name = sights.getName();
        title = sights.getTitle();
        info = sights.getInfo();
        addr = sights.getAddr();
        mapx = sights.getMapx();
        mapy = sights.getMapy();
        img = sights.getImg();
        city = sights.getCity();
        homepage = sights.getHomepage();
        number = sights.getNumber();
        openDate = sights.getOpenDate();
        trafficReport = sights.getTrafficReport();
        like = sights.getLike();
        rate = sights.calculateAverageRating();
        reviews = new ArrayList<>();
        for (Review review : sights.getReview()) {
            reviews.add(new ReviewResponseDto(review));
        }
    }
}
