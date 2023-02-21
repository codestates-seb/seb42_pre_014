package pre14.stackoverflow.questions.dto;

import lombok.*;
import pre14.stackoverflow.questions.entity.Question;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;


public class QuestionDto {
    @Getter
    @ToString
    @RequiredArgsConstructor
    public static class Post{
        private Long questionId;
        @NotBlank(message = "제목을 작성해주세요")
        @Size(max = 100, message = "100자 이내로 작성해 주세요.")
        private String title;
        @Size(min = 10, max = 5000, message = "10자 이상, 5000자 이내로 작성해 주세요.")
        @NotBlank(message = "본문을 작성해주세요")
        private String contents;
    }

    @Getter
    @RequiredArgsConstructor
    @ToString
    public static class Patch{
        private Long questionId;
        @Size(max = 100)
        private String title;
        @Size(min = 10, max = 5000)
        private String contents;
    }

    @Getter
    @Setter
    @RequiredArgsConstructor
    @ToString
    public static class QuestionResponseDto{
        private Long questionId;
        private String title;
        private String contents;
        private Question.QuestionStatus questionStatus;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
    }
}
