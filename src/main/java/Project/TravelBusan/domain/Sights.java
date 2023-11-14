package Project.TravelBusan.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.text.DecimalFormat;
import java.util.List;

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

    @OneToMany(mappedBy = "sights", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Review> review;

    public double calculateAverageRating() {
        if (review == null || review.isEmpty()) {
            return 0.0; // 리뷰가 없을 경우 0.0 반환 또는 다른 적절한 값을 반환
        }

        int sum = 0;
        for (Review review : review) {
            sum += review.getRating();
        }

        // 평균을 계산하고 소수점 첫째 자리까지만 표시
        double averageRating = ((double) sum / review.size());

        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        return Double.parseDouble(decimalFormat.format(averageRating));
    }


}