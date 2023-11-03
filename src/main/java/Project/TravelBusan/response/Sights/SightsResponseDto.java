package Project.TravelBusan.response.Sights;

import Project.TravelBusan.domain.User;
import Project.TravelBusan.domain.Sights;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SightsResponseDto {
    private Long id;
    private String name;
    private String info;
    private String addr;
    private String mapx;
    private String mapy;
    private String img;
    private String city;
    private String like;
    private int rate;
    private String type;
    private User User;


    public static SightsResponseDto toDto(Sights sights) {
        return new SightsResponseDto(
                sights.getId(),
                sights.getName(),
                sights.getInfo(),
                sights.getAddr(),
                sights.getMapx(),
                sights.getMapy(),
                sights.getImg(),
                sights.getCity(),
                sights.getLike(),
                sights.getRate(),
                sights.getType(),
                sights.getUser()
        );
    }

}
