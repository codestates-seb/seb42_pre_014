package pre14.stackoverflow.answer.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import pre14.stackoverflow.member.entity.Member;
import pre14.stackoverflow.questions.entity.Question;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@EntityListeners(AuditingEntityListener.class)//시간 생성해주는 것 LocalDateTime 함께사용
@ToString
public class Answer{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    @Column(nullable = false, length = 99999)
    private String contents;                                      // 대답 내용
    @Column(nullable = false)                                     // 추천 수
    private long voteCount;

    @Enumerated(value = EnumType.STRING)
    private AnswerStatus answerStatus =  AnswerStatus.UNACCEPTED;    // 대답 응답

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;


    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public void setMember(Member member){
        this.member = member;
    }

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @OneToMany(mappedBy = "answer", cascade = CascadeType.REMOVE)
    private List<AnswerVote> answerVoteList = new ArrayList<>();


    public void setQuestion(Question question) {
        this.question = question;
    }

    public enum AnswerStatus {
        ACCEPTED("채택"),
        UNACCEPTED("채택되지 않음");

        @Getter
        private String message;

        AnswerStatus(String message) {
            this.message = message;
        }
    }
}