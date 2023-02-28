package pre14.stackoverflow.questions.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pre14.stackoverflow.questions.dto.QuestionDto;
import pre14.stackoverflow.questions.dto.QuestionVoteDto;
import pre14.stackoverflow.questions.entity.Question;
import pre14.stackoverflow.questions.entity.QuestionVote;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QuestionMapper {
    Question questionPostToQuestion(QuestionDto.Post questionPostDto);

    Question questionPatchDtoToQuestion(QuestionDto.Patch questionPatchDto);
    QuestionVote questionVoteDtoToQuestionVote(QuestionVoteDto requestBody);

    QuestionDto.Response questionToQuestionResponse(Question question);

    List<QuestionDto.Response> questionToQuestionResponseDtos(List<Question> questions);

}
