package pre14.stackoverflow.questions.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import pre14.stackoverflow.member.entity.Member;
import pre14.stackoverflow.tag.Tag;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)//시간 생성해주는 것 LocalDateTime함께사용
@RequiredArgsConstructor
//@ToString //애플리케이션 실행 시 콘솔에 여러정보 확인할 수 있게해주는것
@Table(name = "question")
public class Question{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;
    @Column(nullable = false, length = 100)
    private String title;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String contents;
    @Enumerated(value = EnumType.STRING)
    private QuestionStatus questionStatus = QuestionStatus.QUESTION_REGISTRATION;
    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonIgnore//JPA 무한 참조순환으로 인한 어노테이션 추가
    private Member member;
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt ;
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    public Question(String title, String contents, LocalDateTime createdAt) {
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
    }

    public enum QuestionStatus {
        QUESTION_REGISTRATION("질문 등록"),
        QUESTION_ANSWERED("답변 완료"),
        QUESTION_DELETE("질문 삭제");

        @Getter
        private String status;

        QuestionStatus(String status) {this.status = status;}
    }
}
