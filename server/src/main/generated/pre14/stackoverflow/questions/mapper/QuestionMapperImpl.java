package pre14.stackoverflow.questions.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pre14.stackoverflow.questions.dto.QuestionDto;
import pre14.stackoverflow.questions.entity.Question;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-17T14:17:45+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.17 (Azul Systems, Inc.)"
)
@Component
public class QuestionMapperImpl implements QuestionMapper {

    @Override
    public Question questionPostDtoToQuestion(QuestionDto.Post requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        Question question = new Question();

        question.setTitle( requestBody.getTitle() );
        question.setBody( requestBody.getBody() );

        return question;
    }

    @Override
    public Question questionPatchDtoToQuestion(QuestionDto.Patch questionPatchDto) {
        if ( questionPatchDto == null ) {
            return null;
        }

        Question question = new Question();

        return question;
    }

    @Override
    public QuestionDto.QuestionResponseDto questionToQuestionResponseDto(Question question) {
        if ( question == null ) {
            return null;
        }

        QuestionDto.QuestionResponseDto questionResponseDto = new QuestionDto.QuestionResponseDto();

        if ( question.getQuestionId() != null ) {
            questionResponseDto.setQuestionId( question.getQuestionId() );
        }
        questionResponseDto.setQuestionStatus( question.getQuestionStatus() );

        return questionResponseDto;
    }

    @Override
    public List<QuestionDto.QuestionResponseDto> questionToQuestionResponseDtos(List<Question> questions) {
        if ( questions == null ) {
            return null;
        }

        List<QuestionDto.QuestionResponseDto> list = new ArrayList<QuestionDto.QuestionResponseDto>( questions.size() );
        for ( Question question : questions ) {
            list.add( questionToQuestionResponseDto( question ) );
        }

        return list;
    }
}
