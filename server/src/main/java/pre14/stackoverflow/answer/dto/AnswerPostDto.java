package pre14.stackoverflow.answer.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class AnswerPostDto {
    private Long questionId;
    private Long memberId;

    private String userName;

    @Pattern(regexp = "^\\S+(\\s?\\S+)*$", message = "내용은 공백이 아니어야 합니다.")
    private String contents;
}

