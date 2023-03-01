package pre14.stackoverflow.tag.mapper;

import org.mapstruct.Mapper;
import pre14.stackoverflow.tag.dto.TagDto;
import pre14.stackoverflow.tag.entity.Tag;
import pre14.stackoverflow.tag.dto.TagDto.TagResponseDto;
import pre14.stackoverflow.questions.entity.Question;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TagMapper {
    default Tag tagPostDtoToTag(TagDto.TagPostDto tagPostDto){

        Question question = new Question();
        question.setQuestionId(tagPostDto.getQuestionId());
        Tag tag = new Tag();

        tag.setQuestion(question);
        tag.setHashTag(tagPostDto.getHashTag());

        return tag;
    }

    default TagResponseDto tagToTagResponseDto(Tag tag){
        return TagResponseDto.builder()
                .hashTag(tag.getHashTag())
                .tagId(tag.getTagId())
                .questionId(tag.getQuestion().getQuestionId())
                .build();
    }

    List<TagResponseDto> tagToTagDtos(List<Tag> tags);
}
