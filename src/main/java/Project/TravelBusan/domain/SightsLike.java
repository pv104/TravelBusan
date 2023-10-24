package Project.TravelBusan.domain;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "SIGHTS_LIKE")
public class SightsLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "sights_id")
    private Sights sights;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User User;
}
