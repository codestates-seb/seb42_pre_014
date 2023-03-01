package pre14.stackoverflow.tag.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pre14.stackoverflow.tag.entity.Tag;

import java.util.List;


@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    @Query(value = "select * from tag where question_id = :questionId", nativeQuery = true)
    List<Tag> findAllByQuestionId(long questionId);
}
