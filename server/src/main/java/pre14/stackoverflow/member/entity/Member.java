package pre14.stackoverflow.member.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Enumerated(value = EnumType.STRING)
    @Column(length =20, nullable = false)
    private MemberStatus memberStatus=MemberStatus.MEMBER_ACTIVE;

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

    @Column(nullable = false, name="LAST_MODIFIED_AT")
    private LocalDateTime modifiedAt=LocalDateTime.now();

    public Member(String email,String name, String phone){
        this.email=email;
        this.name=name;
        this.phone=phone;
    }

    public enum MemberStatus{
        MEMBER_ACTIVE("온라인"),
        MEMBER_QUIT("로그아웃");

        @Getter
        private String status;

        MemberStatus(String status){
            this.status=status;
        }
    }
}
