package pre14.stackoverflow.tag;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.impl.StaticMDCBinder;
import pre14.stackoverflow.questions.dto.QuestionDto;

import javax.validation.constraints.NotBlank;
import java.util.List;


public class TagDto {
    @Getter
    public static class Post {
        @NotBlank(message = "태그를 입력해주세요")
        private String tagName;  // 어떻게 배열형식으로 나타낼까?

        private Long questionId;
    }

    @Getter
    @Setter
    @RequiredArgsConstructor
    @ToString
    public static class Response{
        private Long tagId;

        private String tagName;

        //QuestionDto.Response question;
    }
}
