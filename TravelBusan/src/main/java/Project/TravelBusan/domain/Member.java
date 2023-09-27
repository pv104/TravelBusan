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
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 의미 없는 객체의 생성 무분별하게 생성하는 것을 막을 수 있음
@AllArgsConstructor
@Table(name = "member")
@DynamicInsert
public class Member{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //
    @Column(name = "member_id")
    private Long id;

    @Column(name = "id",unique = true)
    private String username;

    private String password;

    private String email;
    private String nickname;

    @CreationTimestamp
    private Timestamp creDate;

    private Timestamp delDate;

    @ColumnDefault("M") // 기본값
    private String grade;

    @ColumnDefault("F")
    private String state;

    public void memberModify(String password, String email){
        this.password = password;
        this.email = email;
    }
}
