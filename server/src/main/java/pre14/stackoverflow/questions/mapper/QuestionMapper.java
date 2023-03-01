package pre14.stackoverflow.questions.mapper;

import org.mapstruct.*;
import pre14.stackoverflow.answer.dto.AnswerDto;
import pre14.stackoverflow.answer.entity.Answer;
import pre14.stackoverflow.member.dto.MemberDto;
import pre14.stackoverflow.member.entity.Member;
import pre14.stackoverflow.questions.dto.QuestionDto;
import pre14.stackoverflow.questions.dto.QuestionVoteDto;
import pre14.stackoverflow.questions.entity.Question;
import pre14.stackoverflow.questions.entity.QuestionVote;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QuestionMapper {
    Question questionPostToQuestion(QuestionDto.Post questionPostDto);
    Question questionPatchDtoToQuestion(QuestionDto.Patch questionPatchDto);

    QuestionVote questionVoteDtoToQuestionVote(QuestionVoteDto requestBody);

    QuestionDto.Response questionToQuestionResponse(Question question);
    QuestionDto.DetailPageResponse questionToQuestionDetailPageResponse(Question question);
    @Mapping(source = "answers", target = "answerCount", qualifiedByName = "countAnswers")
    @Mapping(source = "member.questions", target = "member.numberOfQuestions", qualifiedByName = "countQuestions")
    @Mapping(source = "member.answers", target = "member.numberOfAnswers", qualifiedByName = "countAnswers")
    QuestionDto.TotalPageResponse questionToQuestionTotalPageResponse(Question question);
    @Named("countAnswers")
    default long countAnswers(List<Answer> answers) { return answers.size();}
    @Named("countQuestions")
    default long countNumberOfQuestions(List<Question> questions) { return questions.size();}

    List<QuestionDto.TotalPageResponse> questionToQuestionTotalPageResponseDtos(List<Question> questions);



}
