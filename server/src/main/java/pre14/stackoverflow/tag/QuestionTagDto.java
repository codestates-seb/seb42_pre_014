package pre14.stackoverflow.tag;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;

import javax.validation.constraints.Positive;

@Getter
@RequiredArgsConstructor
public class QuestionTagDto {
        private Long tagId; //필수
        private String tagName;
}
