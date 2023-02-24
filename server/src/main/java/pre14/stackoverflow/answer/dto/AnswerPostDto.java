package pre14.stackoverflow.answer.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@RequiredArgsConstructor
@ToString
public class AnswerPostDto {
    private Long answerId;
    @NotBlank(message = "이름을 작성해주세요")
    private String userName;
    @NotBlank(message = "본문을 작성해주세요")
    private String contents;
    private Long memberId;
}
