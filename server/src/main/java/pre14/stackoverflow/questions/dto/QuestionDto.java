package pre14.stackoverflow.questions.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.mapstruct.Mapper;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class QuestionDto {

    @Getter
    @AllArgsConstructor
    public static class Post{
        @NotBlank
        private String title;

        @NotBlank
        private String body;

        private long memberId;

        public void addMemberId(long memberId){
            Assert.notNull(memberId, "member id must not be null");
            this.memberId = memberId;
        }
    }
}
