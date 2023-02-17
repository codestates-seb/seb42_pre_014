package pre14.stackoverflow.questions.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.springframework.util.Assert;
import pre14.stackoverflow.member.entity.Member;
import pre14.stackoverflow.questions.entity.Question;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


public class QuestionDto {

    @Getter
    @AllArgsConstructor
    public static class Post{
        @NotBlank(message = "제목을 비워두지 마세요")
        private String title;

        @NotBlank(message = "내용을 비워두지 마세요")
        private String body;

        private long memberId;

        public void addMemberId(long memberId){
            Assert.notNull(memberId, "member id must not be null");
            this.memberId = memberId;
        }
    }

    public static class Patch{
        private long questionId;
        private Question.QuestionStatus questionStatus;

        public void setQuestionId(long questionId){this.questionId = questionId;}
    }
    @Getter
    @Setter
    public static class QuestionResponseDto{
        private long questionId;

        @Setter(AccessLevel.NONE)
        private long memberId;

        private Question.QuestionStatus questionStatus;
        private LocalDateTime createAt;

        public void setMemberId(Member member) {
            this.memberId = member.getMemberId();
        }
    }

}
