package pre14.stackoverflow.answer.dto;

import lombok.*;
import pre14.stackoverflow.answer.entity.Answer;
import pre14.stackoverflow.member.dto.MemberDto;
import pre14.stackoverflow.questions.dto.QuestionDto;

import java.time.LocalDateTime;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AnswerResponseDto {
    private Long answerId;
    private String userName;
    private String contents;
    private Answer.AnswerStatus status;                           // 대답 응답
    private LocalDateTime createdAt;                              // 작성시간
    private LocalDateTime modifiedAt;                             // 수정시간
    private MemberDto.Response member;
    private QuestionDto.Response question;

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}
