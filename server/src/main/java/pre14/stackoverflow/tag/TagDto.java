package pre14.stackoverflow.tag;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.impl.StaticMDCBinder;

import javax.validation.constraints.NotBlank;


public class TagDto {
    @Getter
    public static class Post {
        @NotBlank(message = "태그를 입력해주세요")
        private String tagName;
    }
    @Getter
    @Setter
    @RequiredArgsConstructor
    @ToString
    public static class Response{
        private Long tagId;
        private String tagName;
    }
}
