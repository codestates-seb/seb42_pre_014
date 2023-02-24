package pre14.stackoverflow.answer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pre14.stackoverflow.answer.dto.AnswerPatchDto;
import pre14.stackoverflow.answer.dto.AnswerPostDto;
import pre14.stackoverflow.answer.dto.AnswerResponseDto;
import pre14.stackoverflow.answer.entity.Answer;
import pre14.stackoverflow.member.mapper.MemberMapper;
import pre14.stackoverflow.member.service.MemberService;
import pre14.stackoverflow.questions.dto.QuestionDto;
import pre14.stackoverflow.questions.entity.Question;
import pre14.stackoverflow.questions.service.QuestionService;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AnswerMapper{
    Answer answerPostDtoToAnswer(AnswerPostDto answerPostDto);

    Answer answerPatchDtoToAnswer(AnswerPatchDto answerPatchDto);

    AnswerResponseDto answerToAnswerResponse(Answer answer);

    List<AnswerResponseDto> answersToAnswerResponseDtos(List<Answer> answers);

}