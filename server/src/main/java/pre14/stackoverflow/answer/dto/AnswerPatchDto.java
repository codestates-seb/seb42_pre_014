package pre14.stackoverflow.answer.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AnswerPatchDto {
    private Long answerId;
    private String userName;
    private String title;
    private String contents;
}
