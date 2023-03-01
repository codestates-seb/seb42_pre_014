package pre14.stackoverflow.tag.dto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class TagDto {
        @Getter
        @Builder
        public static class TagPostDto {

                private Long QuestionId;

                private String hashTag;

                public void updateQuestionId(Long questionId) {
                        QuestionId = questionId;
                }
        }

        @Getter
        @Builder
        @AllArgsConstructor
        public static class TagResponseDto {
                private Long tagId;

                private Long questionId;

                private String hashTag;
        }
}