package pre14.stackoverflow.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import pre14.stackoverflow.answer.entity.Answer;
import pre14.stackoverflow.answer.entity.AnswerVote;
import pre14.stackoverflow.questions.entity.Question;
import pre14.stackoverflow.questions.entity.QuestionVote;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)//시간 생성해주는 것 LocalDateTime 함께사용
@Table(name = "member")
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
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Answer> answers = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<QuestionVote> questionVotes = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<AnswerVote> answerVotes = new ArrayList<>();

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
