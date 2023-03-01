package pre14.stackoverflow.questions.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pre14.stackoverflow.questions.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

}
