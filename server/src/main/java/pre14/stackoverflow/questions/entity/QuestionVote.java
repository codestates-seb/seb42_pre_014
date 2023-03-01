package pre14.stackoverflow.questions.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
public class QuestionVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long voteId;

    @Enumerated(EnumType.STRING)
    private QuestionVoteStatus questionVoteStatus;

    @Column(name = "member_id")
    private Long memberId;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    public enum QuestionVoteStatus {
        UP("추천"),
        NONE("취소"),
        DOWN("비추천");

        @Getter
        private String status;

        QuestionVoteStatus(String status) {
            this.status = status;
        }
    }
}