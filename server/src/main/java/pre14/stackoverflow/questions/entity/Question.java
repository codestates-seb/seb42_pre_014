package pre14.stackoverflow.questions.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@RequiredArgsConstructor //아무것도 없이 만들어줌/ 편안
@EntityListeners(AuditingEntityListener.class)//시간 생성해주는 것 LocalDateTime함께사용
@ToString //애플리케이션 실행 시 콘솔에 여러정보 확인할 수 있게해주는것
@Table(name = "squestion")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false, length = 5000)
    private String contents;
    @Enumerated(value = EnumType.STRING)
    private QuestionStatus questionStatus = QuestionStatus.QUESTION_REGISTRATION;

    @CreatedDate
    @Column//(nullable = false)
    private LocalDateTime createdAt ;
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    public Question(String title, String contents, LocalDateTime createdAt) {
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
    }

    public enum QuestionStatus {
        QUESTION_REGISTRATION(1, "질문 등록"),
        QUESTION_ANSWERED(2, "답변 완료"),
        QUESTION_DELETE(3, "질문 삭제");

        private int num;
        private String message;

        QuestionStatus(int num, String message) {
            this.num = num;
            this.message = message;
        }
    }
}
