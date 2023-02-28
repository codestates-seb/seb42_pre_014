package pre14.stackoverflow.questions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pre14.stackoverflow.questions.entity.Question;
import pre14.stackoverflow.questions.entity.QuestionVote;

public interface QuestionVoteRepository extends JpaRepository<QuestionVote, Long> {
    QuestionVote findByQuestionAndMemberId(Question question, long memberId);
}