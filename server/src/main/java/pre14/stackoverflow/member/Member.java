package pre14.stackoverflow.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "USERS")
@Table(name = "USERS")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    public Member(long memberId){
        this.memberId=memberId;
    }
    @Column(length = 100, nullable = false)
    private String name;
    @Column(nullable = false, updatable = false, unique = true)
    private String email;
    @Column(length = 13, nullable = false, unique = true)
    private String phone;

    @Column(nullable = false)
    private LocalDateTime createdAt=LocalDateTime.now();

    public Member(String email,String name, String phone){
        this.email=email;
        this.name=name;
        this.phone=phone;
    }
}
