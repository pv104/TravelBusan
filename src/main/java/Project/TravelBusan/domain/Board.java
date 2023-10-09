package Project.TravelBusan.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "BOARD")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    private String title;
    private String nickname;
    private String content;
    private String img;

    @ColumnDefault("0")
    private Long like;

    @ColumnDefault("0")
    private Long visit;

    @CreationTimestamp
    @Column(name = "cre_date")
    private Timestamp creDate;

    @Column(name = "del_date")
    private Timestamp delDate;

    @ColumnDefault("N")
    private String state;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}

