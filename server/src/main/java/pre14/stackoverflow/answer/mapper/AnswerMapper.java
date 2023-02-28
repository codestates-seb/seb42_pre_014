package pre14.stackoverflow.answer.mapper;

import org.mapstruct.Mapper;
import pre14.stackoverflow.answer.dto.AnswerDto;
import pre14.stackoverflow.answer.dto.AnswerVoteDto;
import pre14.stackoverflow.answer.entity.Answer;
import pre14.stackoverflow.answer.entity.AnswerVote;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnswerMapper{
    Answer answerPostDtoToAnswer(AnswerDto.Post answerPostDto);

    Answer answerPatchDtoToAnswer(AnswerDto.Patch answerPatchDto);
    AnswerVote answerVoteDtoToAnswerVote(AnswerVoteDto requestBody);

    AnswerDto.Response answerToAnswerResponse(Answer answer);

    List<AnswerDto.Response> answersToAnswerResponseDtos(List<Answer> answers);

}