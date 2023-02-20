package pre14.stackoverflow.answer.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pre14.stackoverflow.answer.dto.AnswerPatchDto;
import pre14.stackoverflow.answer.dto.AnswerPostDto;
import pre14.stackoverflow.answer.dto.AnswerResponseDto;
import pre14.stackoverflow.answer.entity.Answer;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-17T14:32:29+0900",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.17 (Azul Systems, Inc.)"
)
@Component
public class AnswerMapperImpl implements AnswerMapper {

    @Override
    public Answer answerPostToAnswer(AnswerPostDto answerPostDto) {
        if ( answerPostDto == null ) {
            return null;
        }

        Answer answer = new Answer();

        answer.setUserName( answerPostDto.getUserName() );
        answer.setTitle( answerPostDto.getTitle() );
        answer.setContents( answerPostDto.getContents() );

        return answer;
    }

    @Override
    public Answer answerPatchToAnswer(AnswerPatchDto answerPatchDto) {
        if ( answerPatchDto == null ) {
            return null;
        }

        Answer answer = new Answer();

        answer.setUserName( answerPatchDto.getUserName() );
        answer.setTitle( answerPatchDto.getTitle() );
        answer.setContents( answerPatchDto.getContents() );

        return answer;
    }

    @Override
    public AnswerResponseDto answerToAnswerResponse(Answer answer) {
        if ( answer == null ) {
            return null;
        }

        AnswerResponseDto answerResponseDto = new AnswerResponseDto();

        answerResponseDto.setAnswerId( answer.getAnswerId() );
        answerResponseDto.setUserName( answer.getUserName() );
        answerResponseDto.setTitle( answer.getTitle() );
        answerResponseDto.setContents( answer.getContents() );

        return answerResponseDto;
    }

    @Override
    public List<AnswerResponseDto> answersToAnswerResponseDto(List<Answer> answer) {
        if ( answer == null ) {
            return null;
        }

        List<AnswerResponseDto> list = new ArrayList<AnswerResponseDto>( answer.size() );
        for ( Answer answer1 : answer ) {
            list.add( answerToAnswerResponse( answer1 ) );
        }

        return list;
    }
}