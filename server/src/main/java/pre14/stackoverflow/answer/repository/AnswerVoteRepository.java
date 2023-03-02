package pre14.stackoverflow.answer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pre14.stackoverflow.answer.entity.Answer;
import pre14.stackoverflow.answer.entity.AnswerVote;
import pre14.stackoverflow.member.entity.Member;
import pre14.stackoverflow.questions.entity.Question;
import pre14.stackoverflow.questions.entity.QuestionVote;

@Repository
public interface AnswerVoteRepository extends JpaRepository<AnswerVote, Long> {
    AnswerVote findByAnswerAndMember(Answer answer, Member member);
}