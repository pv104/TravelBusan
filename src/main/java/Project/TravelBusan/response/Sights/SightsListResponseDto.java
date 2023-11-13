package Project.TravelBusan.response.Sights;

import Project.TravelBusan.domain.Sights;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SightsListResponseDto {
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
    private int rate;
    private String type;


    public static SightsListResponseDto toDto(Sights sights) {
        return new SightsListResponseDto(
                sights.getId(),
                sights.getName(),
                sights.getTitle(),
                sights.getInfo(),
                sights.getAddr(),
                sights.getMapx(),
                sights.getMapy(),
                sights.getImg(),
                sights.getCity(),
                sights.getHomepage(),
                sights.getNumber(),
                sights.getOpenDate(),
                sights.getTrafficReport(),
                sights.getLike(),
                sights.getRate(),
                sights.getType()
        );
    }

}
