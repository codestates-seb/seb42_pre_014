package pre14.stackoverflow.questions.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import pre14.stackoverflow.answer.entity.Answer;
import pre14.stackoverflow.member.entity.Member;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@EntityListeners(AuditingEntityListener.class)//시간 생성해주는 것 LocalDateTime함께사용
@RequiredArgsConstructor
@ToString //애플리케이션 실행 시 콘솔에 여러정보 확인할 수 있게해주는것
public class Question{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long questionId;
    @Column(nullable = false, length = 100)
    private String title;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String contents;
    @Column
    private long voteCount;
    @Enumerated(value = EnumType.STRING)
    private QuestionStatus questionStatus = QuestionStatus.QUESTION_REGISTRATION;

    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonBackReference
    private Member member;


    // 1:N 단방향 맵핑 자동구성 ( tag entity 안만들고 가능 ) -> N:N 매핑 구현 get post patch
    @ElementCollection(targetClass = String.class)
    @Column
    private List<String> tag;

    // 질문 ~ 답변 (양방향)
    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Answer> answers = new ArrayList<>();

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<QuestionVote> votes = new ArrayList<>();

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    public enum QuestionStatus {
        QUESTION_REGISTRATION("질문 등록"),
        QUESTION_ANSWERED("답변 완료"),
        QUESTION_DELETE("질문 삭제");

        @Getter
        private String status;

        QuestionStatus(String status) {this.status = status;}
    }
}
