package pre14.stackoverflow.questions.dto;

import lombok.*;
import pre14.stackoverflow.answer.dto.AnswerDto;
import pre14.stackoverflow.member.dto.MemberDto;
import pre14.stackoverflow.questions.entity.Question;
import pre14.stackoverflow.tag.dto.TagDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;


public class QuestionDto {
    @Getter
    @RequiredArgsConstructor
    @ToString
    public static class Post{
        @Positive
        @NotNull
        private Long memberId;

        @NotBlank(message = "제목을 작성해주세요")
        @Size(max = 100, message = "100자 이내로 작성해 주세요.")
        private String title;

        @NotBlank(message = "내용은 공백이 아니어야 합니다.")
        private String contents;

//      private Long answerId;

//      private List<QuestionTag> tagList;
    }

    @Getter
    @RequiredArgsConstructor
    @Setter
    @ToString
    public static class Patch{
        private Long questionId;
        @Size(max = 100, message = "제목을 작성해주세요")
        private String title;
        @Size(min = 10, max = 5000, message = "내용은 공백이 아니어야 합니다.")
        private String contents;
    }

    @Getter
    @Setter
    @RequiredArgsConstructor
    @ToString
    public static class Response {
        private Long questionId;
        private String title;
        private String contents;
        private long voteCount;
        private MemberDto.Response member;
        private Question.QuestionStatus questionStatus;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private List<TagDto.TagResponseDto> tags;
        private List<AnswerDto.Response> answers;


    }
    @Getter
    @Setter
    @NoArgsConstructor
    public static class TotalPageResponse {
        private Long questionId;
        private String title;
        private String contents;
        private long voteCount;
        private MemberDto.Response member;
        private Question.QuestionStatus questionStatus;
        private List<TagDto.TagResponseDto> tags;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private long answerCount;
        
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class DetailPageResponse {
        private Long questionId;
        private String title;
        private String contents;
        private long voteCount;
        private MemberDto.Response member;
        private Question.QuestionStatus questionStatus;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private List<AnswerDto.InfoResponse> answers;
    }
}
