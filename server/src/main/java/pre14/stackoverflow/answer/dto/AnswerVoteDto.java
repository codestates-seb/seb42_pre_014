package pre14.stackoverflow.answer.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
public class AnswerVoteDto {
    @Positive
    @NotNull
    private Long memberId;
}