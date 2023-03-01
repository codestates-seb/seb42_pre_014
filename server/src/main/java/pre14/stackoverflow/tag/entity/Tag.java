package pre14.stackoverflow.tag.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pre14.stackoverflow.audit.Auditable;
import pre14.stackoverflow.questions.entity.Question;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Tag extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long tagId;


    @JoinColumn(name = "question_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Question question;

    @Column(nullable = false)
    private String hashTag;

    public void addQuestion(Question question) {
        this.question = question;
        question.getTags().add(this);
    }
}

