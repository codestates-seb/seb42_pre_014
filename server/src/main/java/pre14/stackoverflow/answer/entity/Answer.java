package pre14.stackoverflow.answer.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@Table(name = "answer")
@ToString
@EntityListeners(AuditingEntityListener.class)
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long answerId;
    @Column(nullable = false, length = 30)
    private String userName;                                     // 유저 이름
    @Column(nullable = false, length = 99999)
    private String contents;                                      // 대답 내용
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt;                              // 작성시간
    @LastModifiedDate
    private LocalDateTime modifiedAt;                            // 수정시간

}