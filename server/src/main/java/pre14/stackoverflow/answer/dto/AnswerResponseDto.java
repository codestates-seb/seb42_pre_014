package pre14.stackoverflow.answer.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class AnswerResponseDto {
    private Long answerId;
    private String userName;
    private String title;
    private String contents;
}
