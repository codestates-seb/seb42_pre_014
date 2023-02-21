package pre14.stackoverflow.answer.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import pre14.stackoverflow.answer.entity.Answer;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class AnswerResponseDto {
    private Long answerId;
    private String userName;
    private String contents;
    private LocalDateTime createdAt;                              // 작성시간
    private LocalDateTime modifiedAt;                             // 수정시간
}
