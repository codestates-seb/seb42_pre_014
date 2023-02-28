package pre14.stackoverflow.answer.entity;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@RequiredArgsConstructor
@Getter
@Setter
@Entity
public class AnswerVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voteId;

    @Enumerated(EnumType.STRING)
    private AnswerVoteStatus answerVoteStatus;

    @Column(name = "member_id")
    private Long memberId;

    @ManyToOne
    @JoinColumn(name = "answer_id")
    private Answer answer;


    public enum AnswerVoteStatus {
        UP("추천"),
        DOWN("비추천");

        @Getter
        private String massage;

        AnswerVoteStatus(String massage) {
            this.massage = massage;
        }
    }
}
