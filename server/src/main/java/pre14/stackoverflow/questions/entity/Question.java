package pre14.stackoverflow.questions.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;
    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 5000)
    private String contents;

    private QuestionStatus questionStatus = QuestionStatus.QUESTION_REGISTRATION;
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;


    public enum QuestionStatus {
        QUESTION_REGISTRATION(1,"질문 등록"),
        QUESTION_ANSWERED(2,"답변 완료"),
        QUESTION_DELETE(3,"질문 삭제");

        private int num;
        private String message;

        QuestionStatus(int num, String message) {
            this.num = num;
            this.message = message;
        }
    }



}
