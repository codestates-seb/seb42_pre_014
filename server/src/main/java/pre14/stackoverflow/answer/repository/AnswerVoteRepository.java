package pre14.stackoverflow.answer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pre14.stackoverflow.answer.entity.Answer;
import pre14.stackoverflow.answer.entity.AnswerVote;

@Repository
public interface AnswerVoteRepository extends JpaRepository<AnswerVote, Long> {
}