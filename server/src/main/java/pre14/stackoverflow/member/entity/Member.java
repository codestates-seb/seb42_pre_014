package pre14.stackoverflow.member.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
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
//@AllArgsConstructor
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Enumerated(value = EnumType.STRING)
    @Column(length =20, nullable = false)
    private MemberStatus memberStatus=MemberStatus.MEMBER_ACTIVE;

    @Column(length = 100, nullable = false)
    private String name;
    @Column(nullable = false, updatable = false, unique = true)
    private String email;
    @Column(length = 13, nullable = false, unique = true)
    private String phone;

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

    @Column(nullable = false)
    private String password;


    @Column(nullable = false)
    private LocalDateTime createdAt=LocalDateTime.now();




    public Member(String email,String name, String phone,String password){
        this.email=email;
        this.name=name;
        this.phone=phone;
        this.password=password;
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

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

}
