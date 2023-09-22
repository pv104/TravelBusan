package Project.TravelBusan.domain;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 의미 없는 객체의 생성 무분별하게 생성하는 것을 막을 수 있음
@AllArgsConstructor
@Table(name = "member")
public class Member{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String username;

    private String password;

    private String email;

    public void modify(String password,String email){
        this.password = password;
        this.email = email;
    }

}
