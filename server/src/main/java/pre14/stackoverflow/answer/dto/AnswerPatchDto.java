package pre14.stackoverflow.answer.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@RequiredArgsConstructor
public class AnswerPatchDto {
    private Long answerId;
    @Pattern(regexp = "^\\S+(\\s?\\S+)*$", message = "내용은 공백이 아니어야 합니다.")
    private String contents;
}