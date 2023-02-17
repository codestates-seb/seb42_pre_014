package pre14.stackoverflow.answer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pre14.stackoverflow.answer.dto.AnswerPatchDto;
import pre14.stackoverflow.answer.dto.AnswerPostDto;
import pre14.stackoverflow.answer.dto.AnswerResponseDto;
import pre14.stackoverflow.answer.entity.Answer;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AnswerMapper{
    Answer answerPostToAnswer(AnswerPostDto answerPostDto);
    Answer answerPatchToAnswer(AnswerPatchDto answerPatchDto);
    AnswerResponseDto answerToAnswerResponse(Answer answer);

    List<AnswerResponseDto> answersToAnswerResponseDto(List<Answer> answer);
}
