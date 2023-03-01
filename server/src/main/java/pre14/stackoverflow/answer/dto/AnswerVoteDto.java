package pre14.stackoverflow.answer.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
public class AnswerVoteDto {
    private Long answerId;
    @Positive
    @NotNull
    private Long memberId;
}