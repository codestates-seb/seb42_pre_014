package pre14.stackoverflow.questions.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pre14.stackoverflow.answer.entity.Answer;
import pre14.stackoverflow.member.entity.Member;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String body;

    @ElementCollection
    private List<String> tags;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private QuestionStatus questionStatus = QuestionStatus.QUESTION_REGISTRATION;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

//    @OneToOne(mappedBy = "question")
//    private Answer answer;

//    @Setter(AccessLevel.NONE)  // 투표관련 부분 추가 예정
//    @OneToMany(mappedBy = "question")
//    private List<Vote> votes = new ArrayList<>();

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false, name = "LAST_MODIFIED_AT")
    private LocalDateTime modifiedAt = LocalDateTime.now();



    public enum QuestionStatus {
        QUESTION_REGISTRATION("질문 등록"),
        QUESTION_ANSWERED("답변 완료"),
        QUESTION_DELETE("질문 삭제");

        @Getter
        private String string;

        QuestionStatus(String string) {
            this.string = string;
        }
    }

//    public void setAnswer(Answer answer){
//        this.answer = answer;
//
//    }
}
