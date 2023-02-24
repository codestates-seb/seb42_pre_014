package pre14.stackoverflow.answer.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pre14.stackoverflow.answer.entity.Answer;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class AnswerResponseDto {
    private Long answerId;
    private String userName;
    private String contents;
    private Answer.AnswerStatus status;                           // 대답 응답
    private LocalDateTime createdAt;                              // 작성시간
    private LocalDateTime modifiedAt;                             // 수정시간
    private Long memberId; //sungstii
    private Long questionId;
}
