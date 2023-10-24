package Project.TravelBusan.request;


import Project.TravelBusan.domain.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SightsRequestDto {
    private String name;
    private String info;
    private String addr;
    private String mapx;
    private String mapy;
    private String img;
    private String city;
    private String type;
}
