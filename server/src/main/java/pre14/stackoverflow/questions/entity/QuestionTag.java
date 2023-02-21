package pre14.stackoverflow.questions.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class QuestionTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionTagId;
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;


}
