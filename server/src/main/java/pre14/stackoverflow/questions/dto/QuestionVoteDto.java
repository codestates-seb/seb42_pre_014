package pre14.stackoverflow.questions.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
public class QuestionVoteDto {
    @Positive
    @NotNull
    private Long memberId;

}
