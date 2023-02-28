package pre14.stackoverflow.answer.dto;


import lombok.*;
import pre14.stackoverflow.answer.entity.Answer;
import pre14.stackoverflow.answer.entity.AnswerVote;
import pre14.stackoverflow.member.dto.MemberDto;
import pre14.stackoverflow.questions.dto.QuestionDto;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class AnswerDto {
    @Getter
    @Setter
    @RequiredArgsConstructor
    @ToString
    public static class Post {
        private Long questionId;
        private Long memberId;

        private String nickName;

        @NotBlank(message = "내용은 공백이 아니어야 합니다.")
        private String contents;
    }

    @Getter
    @Setter
    @RequiredArgsConstructor
    public static class Patch {
        private Long answerId;
        @NotBlank(message = "내용은 공백이 아니어야 합니다.")
        private String contents;

    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class Response {
        private Long answerId;
        private String nickName;
        private String contents;
        private int score;
        private Answer.AnswerStatus answerStatus;                     // 대답 응답
        private LocalDateTime createdAt;                              // 작성시간
        private LocalDateTime modifiedAt;                             // 수정시간
        private MemberDto.Response member;
        private QuestionDto.Response question;


        public void setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
        }

        public void setModifiedAt(LocalDateTime modifiedAt) {
            this.modifiedAt = modifiedAt;
        }
    }
}
