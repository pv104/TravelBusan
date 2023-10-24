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
    private String content;
    private String nickname;
    private String img;

    @ColumnDefault("0")
    @Column(name = "like_count")
    private Long likeCount;

    @ColumnDefault("0")
    private Long visit;

    @CreationTimestamp
    @Column(name = "credate")
    private Timestamp creDate;

    @Column(name = "deldate")
    private Timestamp delDate;

    @ColumnDefault("N")
    private String state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public void modifyBoard(String title, String content) {
        this.title = title;
        this.content = content;
    }
}

