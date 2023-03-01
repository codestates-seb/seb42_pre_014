package pre14.stackoverflow.answer.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pre14.stackoverflow.answer.entity.Answer;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-22T16:48:28+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.17 (Azul Systems, Inc.)"
)
@Component
public class AnswerMapperImpl implements AnswerMapper {

    @Override
    public Answer answerPostToAnswer(AnswerPostDto answerPostDto) {
        if ( answerPostDto == null ) {
            return null;
        }

        Answer answer = new Answer();

        if ( answerPostDto.getAnswerId() != null ) {
            answer.setAnswerId( answerPostDto.getAnswerId() );
        }
        answer.setUserName( answerPostDto.getUserName() );
        answer.setContents( answerPostDto.getContents() );

        return answer;
    }

    @Override
    public Answer answerPatchToAnswer(AnswerPatchDto answerPatchDto) {
        if ( answerPatchDto == null ) {
            return null;
        }

        Answer answer = new Answer();

        if ( answerPatchDto.getAnswerId() != null ) {
            answer.setAnswerId( answerPatchDto.getAnswerId() );
        }
        answer.setUserName( answerPatchDto.getUserName() );
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
        answerResponseDto.setContents( answer.getContents() );
        answerResponseDto.setCreatedAt( answer.getCreatedAt() );
        answerResponseDto.setModifiedAt( answer.getModifiedAt() );

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
