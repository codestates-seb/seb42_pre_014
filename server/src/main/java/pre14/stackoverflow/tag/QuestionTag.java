package pre14.stackoverflow.tag;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pre14.stackoverflow.questions.entity.Question;
import pre14.stackoverflow.tag.Tag;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class QuestionTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionTagId;
    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Question question;
    @ManyToOne
    @JoinColumn(name = "TAG_ID")
    private Tag tag;
}