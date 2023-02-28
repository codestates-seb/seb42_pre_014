package pre14.stackoverflow.questions.mapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pre14.stackoverflow.member.dto.MemberDto;
import pre14.stackoverflow.member.entity.Member;
import pre14.stackoverflow.questions.dto.QuestionDto;
import pre14.stackoverflow.questions.dto.QuestionVoteDto;
import pre14.stackoverflow.questions.entity.Question;
import pre14.stackoverflow.questions.entity.QuestionVote;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-28T18:30:09+0900",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.jar, environment: Java 11.0.17 (Azul Systems, Inc.)"
)
@Component
public class QuestionMapperImpl implements QuestionMapper {

    @Override
    public Question questionPostToQuestion(QuestionDto.Post questionPostDto) {
        if ( questionPostDto == null ) {
            return null;
        }

        Question question = new Question();

        question.setTitle( questionPostDto.getTitle() );
        question.setContents( questionPostDto.getContents() );

        return question;
    }

    @Override
    public Question questionPatchDtoToQuestion(QuestionDto.Patch questionPatchDto) {
        if ( questionPatchDto == null ) {
            return null;
        }

        Question question = new Question();

        question.setQuestionId( questionPatchDto.getQuestionId() );
        question.setTitle( questionPatchDto.getTitle() );
        question.setContents( questionPatchDto.getContents() );

        return question;
    }

    @Override
    public QuestionVote questionVoteDtoToQuestionVote(QuestionVoteDto requestBody) {
        if ( requestBody == null ) {
            return null;
        }

        QuestionVote questionVote = new QuestionVote();

        questionVote.setMemberId( requestBody.getMemberId() );

        return questionVote;
    }

    @Override
    public QuestionDto.Response questionToQuestionResponse(Question question) {
        if ( question == null ) {
            return null;
        }

        QuestionDto.Response response = new QuestionDto.Response();

        response.setQuestionId( question.getQuestionId() );
        response.setTitle( question.getTitle() );
        response.setContents( question.getContents() );
        response.setScore( question.getScore() );
        response.setMember( memberToResponse( question.getMember() ) );
        response.setQuestionStatus( question.getQuestionStatus() );
        response.setCreatedAt( question.getCreatedAt() );
        response.setModifiedAt( question.getModifiedAt() );

        return response;
    }

    @Override
    public List<QuestionDto.Response> questionToQuestionResponseDtos(List<Question> questions) {
        if ( questions == null ) {
            return null;
        }

        List<QuestionDto.Response> list = new ArrayList<QuestionDto.Response>( questions.size() );
        for ( Question question : questions ) {
            list.add( questionToQuestionResponse( question ) );
        }

        return list;
    }

    protected MemberDto.Response memberToResponse(Member member) {
        if ( member == null ) {
            return null;
        }

        long memberId = 0L;
        String email = null;
        String name = null;
        String phone = null;
        LocalDateTime createdAt = null;
        LocalDateTime modifiedAt = null;
        Member.MemberStatus memberStatus = null;

        if ( member.getMemberId() != null ) {
            memberId = member.getMemberId();
        }
        email = member.getEmail();
        name = member.getName();
        phone = member.getPhone();
        createdAt = member.getCreatedAt();
        modifiedAt = member.getModifiedAt();
        memberStatus = member.getMemberStatus();

        MemberDto.Response response = new MemberDto.Response( memberId, email, name, phone, createdAt, modifiedAt, memberStatus );

        return response;
    }
}
