package pre14.stackoverflow.answer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@Table(name = "answer")
@ToString
@EntityListeners(AuditingEntityListener.class)
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long answerId;
    @Column(nullable = false, length = 30)
    private String userName;                                     // 유저 이름
    @Column(nullable = false, length = 99999)
    private String contents;                                      // 대답 내용
    private AnswerStatus status = AnswerStatus.ANSWER_WAITING;    // 대답 응답
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt;                              // 작성시간
    @LastModifiedDate
    private LocalDateTime modifiedAt;                            // 수정시간
    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonIgnore//JPA 무한 참조순환으로 인한 어노테이션 추가
    private Member member;

    @ManyToOne
    @JoinColumn(name = "question_id")
    @JsonIgnore//JPA 무한 참조순환으로 인한 어노테이션 추가
    private Question question;

    public enum AnswerStatus {
        ANSWER_WAITING("응답 대기"),
        ANSWER_COMPLETED("응답 완료");


        private String message;

        AnswerStatus(String message) {
            this.message = message;
        }
    }
}
