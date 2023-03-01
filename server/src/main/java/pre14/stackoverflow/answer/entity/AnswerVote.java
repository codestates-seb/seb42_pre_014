package pre14.stackoverflow.answer.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pre14.stackoverflow.member.entity.Member;

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

    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonBackReference
    private Member member;

    @ManyToOne
    @JoinColumn(name = "answer_id")
    @JsonBackReference
    private Answer answer;


    public enum AnswerVoteStatus {
        UP,
        NONE,
        DOWN
    }
}