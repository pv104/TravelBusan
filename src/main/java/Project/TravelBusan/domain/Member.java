package Project.TravelBusan.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.sql.Timestamp;


@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicInsert
@Table(name = "MEMBER")
public class Member{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "id", unique = true)
    private String username;

    private String password;

    private String email;
    private String nickname;

    @CreationTimestamp
    @Column(name = "cre_date")
    private Timestamp creDate;

    @Column(name = "del_date")
    private Timestamp delDate;

    @ColumnDefault("F")
    private String state;

    @ColumnDefault("M") // 기본값
    private String grade;


    public void memberModify(String password, String email){
        this.password = password;
        this.email = email;
    }
}
