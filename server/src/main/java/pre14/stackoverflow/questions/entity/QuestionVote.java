package pre14.stackoverflow.questions.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pre14.stackoverflow.member.entity.Member;

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

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    public enum QuestionVoteStatus {
        UP,
        NONE,
        DOWN

    }
}