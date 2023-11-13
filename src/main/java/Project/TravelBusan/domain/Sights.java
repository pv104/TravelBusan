package Project.TravelBusan.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "SIGHTS")
public class Sights {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sights_id")
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

    @Column(name = "open_date")
    private String openDate;
    @Column(name = "traffic_report")
    private String trafficReport;

    @ColumnDefault("0")
    private String like;

    private int rate;

    @Column(name = "isSights")
    private String type;

//    @ManyToOne(fetch = LAZY)
//    @JoinColumn(name = "user_id")
//    private User user;

}