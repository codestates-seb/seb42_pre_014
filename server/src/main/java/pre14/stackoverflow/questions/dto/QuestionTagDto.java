package pre14.stackoverflow.questions.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;

import javax.validation.constraints.Positive;

@Getter
@RequiredArgsConstructor
public class QuestionTagDto {
        private Long tagId; //필수
        private String tagName;


//    private long memberId;
//
//    public void addMemberId(long memberId){
//        Assert.notNull(memberId, "member id must not be null");
//        this.memberId = memberId;
//    }
}
