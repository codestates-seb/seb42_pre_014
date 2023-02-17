package pre14.stackoverflow.questions.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import pre14.stackoverflow.questions.dto.QuestionDto;
import pre14.stackoverflow.questions.entity.Question;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface QuestionMapper {
    Question questionPostDtoToQuestion(QuestionDto.Post requestBody);

    Question questionPatchDtoToQuestion(QuestionDto.Patch questionPatchDto);

    QuestionDto.QuestionResponseDto questionToQuestionResponseDto(Question question);

    List<QuestionDto.QuestionResponseDto> questionToQuestionResponseDtos(List<Question> questions);

}
