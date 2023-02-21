package pre14.stackoverflow.questions.dto;

import lombok.*;
import pre14.stackoverflow.questions.entity.Question;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


public class QuestionDto {
    @Getter
    @ToString
    @RequiredArgsConstructor
    public static class Post{
        private Long questionId;
        @NotBlank(message = "제목을 비워두지 마세요")
        private String title;
        @NotBlank(message = "내용을 비워두지 마세요")
        private String contents;
        private Long memberId;
    }

    @Getter
    @RequiredArgsConstructor
    @ToString
    public static class Patch{
        private Long questionId;
        private String title;
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

        private Long memberId;
    }

}
