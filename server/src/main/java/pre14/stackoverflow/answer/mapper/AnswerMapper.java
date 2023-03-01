package pre14.stackoverflow.answer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pre14.stackoverflow.answer.dto.AnswerDto;
import pre14.stackoverflow.answer.dto.AnswerVoteDto;
import pre14.stackoverflow.answer.entity.Answer;
import pre14.stackoverflow.answer.entity.AnswerVote;
import pre14.stackoverflow.questions.entity.Question;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnswerMapper{
    Answer answerPostDtoToAnswer(AnswerDto.Post answerPostDto);

    Answer answerPatchDtoToAnswer(AnswerDto.Patch answerPatchDto);
    AnswerVote answerVoteDtoToAnswerVote(AnswerVoteDto requestBody);

    AnswerDto.Response answerToAnswerResponse(Answer answer);
    @Mapping(source = "question", target = "questionId", qualifiedByName = "questionId")
    @Mapping(source = "member.questions", target = "member.numberOfQuestions", qualifiedByName = "countQuestions")
    @Mapping(source = "member.answers", target = "member.numberOfAnswers", qualifiedByName = "countAnswers")
    AnswerDto.InfoResponse answerToAnswerInfoResponse(Answer answer);
    // tqrget은 InfoResponse에 필요한 반환값의 변수명, @Named와 qualifiedByName은 같아야함
    // source는 Answer에서 Question으로 들어갈 필드 값

    @Named("questionId")
    default long questionId(Question question) {return question.getQuestionId();}
    @Named("countAnswers")
    default long countAnswers(List<Answer> answers) { return answers.size();}
    @Named("countQuestions")
    default long countNumberOfQuestions(List<Question> questions) { return questions.size();}

    List<AnswerDto.InfoResponse> answersToAnswerInfoResponseDtos(List<Answer> answers);

}