package pre14.stackoverflow.questions.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import pre14.stackoverflow.answer.dto.AnswerDto;
import pre14.stackoverflow.answer.entity.Answer;
import pre14.stackoverflow.member.dto.MemberDto;
import pre14.stackoverflow.member.entity.Member;
import pre14.stackoverflow.questions.dto.QuestionDto;
import pre14.stackoverflow.questions.dto.QuestionVoteDto;
import pre14.stackoverflow.questions.entity.Question;
import pre14.stackoverflow.questions.entity.QuestionVote;
import pre14.stackoverflow.tag.dto.TagDto;
import pre14.stackoverflow.tag.entity.Tag;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-01T22:40:00+0900",
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
        response.setVoteCount( question.getVoteCount() );
        response.setMember( memberToResponse( question.getMember() ) );
        response.setQuestionStatus( question.getQuestionStatus() );
        response.setCreatedAt( question.getCreatedAt() );
        response.setModifiedAt( question.getModifiedAt() );
        response.setTags( tagListToTagResponseDtoList( question.getTags() ) );
        response.setAnswers( answerListToResponseList( question.getAnswers() ) );

        return response;
    }

    @Override
    public QuestionDto.DetailPageResponse questionToQuestionDetailPageResponse(Question question) {
        if ( question == null ) {
            return null;
        }

        QuestionDto.DetailPageResponse detailPageResponse = new QuestionDto.DetailPageResponse();

        detailPageResponse.setQuestionId( question.getQuestionId() );
        detailPageResponse.setTitle( question.getTitle() );
        detailPageResponse.setContents( question.getContents() );
        detailPageResponse.setVoteCount( question.getVoteCount() );
        detailPageResponse.setMember( memberToResponse( question.getMember() ) );
        detailPageResponse.setQuestionStatus( question.getQuestionStatus() );
        detailPageResponse.setCreatedAt( question.getCreatedAt() );
        detailPageResponse.setModifiedAt( question.getModifiedAt() );
        detailPageResponse.setAnswers( answerListToInfoResponseList( question.getAnswers() ) );

        return detailPageResponse;
    }

    @Override
    public QuestionDto.TotalPageResponse questionToQuestionTotalPageResponse(Question question) {
        if ( question == null ) {
            return null;
        }

        QuestionDto.TotalPageResponse totalPageResponse = new QuestionDto.TotalPageResponse();

        totalPageResponse.setMember( memberToResponse1( question.getMember() ) );
        totalPageResponse.setAnswerCount( countAnswers( question.getAnswers() ) );
        totalPageResponse.setQuestionId( question.getQuestionId() );
        totalPageResponse.setTitle( question.getTitle() );
        totalPageResponse.setContents( question.getContents() );
        totalPageResponse.setVoteCount( question.getVoteCount() );
        totalPageResponse.setQuestionStatus( question.getQuestionStatus() );
        totalPageResponse.setTags( tagListToTagResponseDtoList( question.getTags() ) );
        totalPageResponse.setCreatedAt( question.getCreatedAt() );
        totalPageResponse.setModifiedAt( question.getModifiedAt() );

        return totalPageResponse;
    }

    @Override
    public List<QuestionDto.TotalPageResponse> questionToQuestionTotalPageResponseDtos(List<Question> questions) {
        if ( questions == null ) {
            return null;
        }

        List<QuestionDto.TotalPageResponse> list = new ArrayList<QuestionDto.TotalPageResponse>( questions.size() );
        for ( Question question : questions ) {
            list.add( questionToQuestionTotalPageResponse( question ) );
        }

        return list;
    }

    protected MemberDto.Response memberToResponse(Member member) {
        if ( member == null ) {
            return null;
        }

        MemberDto.Response response = new MemberDto.Response();

        if ( member.getMemberId() != null ) {
            response.setMemberId( member.getMemberId() );
        }
        response.setEmail( member.getEmail() );
        response.setName( member.getName() );
        response.setPhone( member.getPhone() );
        response.setCreatedAt( member.getCreatedAt() );
        response.setModifiedAt( member.getModifiedAt() );
        response.setMemberStatus( member.getMemberStatus() );

        return response;
    }

    protected TagDto.TagResponseDto tagToTagResponseDto(Tag tag) {
        if ( tag == null ) {
            return null;
        }

        TagDto.TagResponseDto.TagResponseDtoBuilder tagResponseDto = TagDto.TagResponseDto.builder();

        tagResponseDto.tagId( tag.getTagId() );
        tagResponseDto.hashTag( tag.getHashTag() );

        return tagResponseDto.build();
    }

    protected List<TagDto.TagResponseDto> tagListToTagResponseDtoList(List<Tag> list) {
        if ( list == null ) {
            return null;
        }

        List<TagDto.TagResponseDto> list1 = new ArrayList<TagDto.TagResponseDto>( list.size() );
        for ( Tag tag : list ) {
            list1.add( tagToTagResponseDto( tag ) );
        }

        return list1;
    }

    protected AnswerDto.Response answerToResponse(Answer answer) {
        if ( answer == null ) {
            return null;
        }

        AnswerDto.Response response = new AnswerDto.Response();

        response.setAnswerId( answer.getAnswerId() );
        response.setContents( answer.getContents() );
        response.setVoteCount( answer.getVoteCount() );
        response.setAnswerStatus( answer.getAnswerStatus() );
        response.setCreatedAt( answer.getCreatedAt() );
        response.setModifiedAt( answer.getModifiedAt() );
        response.setMember( memberToResponse( answer.getMember() ) );

        return response;
    }

    protected List<AnswerDto.Response> answerListToResponseList(List<Answer> list) {
        if ( list == null ) {
            return null;
        }

        List<AnswerDto.Response> list1 = new ArrayList<AnswerDto.Response>( list.size() );
        for ( Answer answer : list ) {
            list1.add( answerToResponse( answer ) );
        }

        return list1;
    }

    protected AnswerDto.InfoResponse answerToInfoResponse(Answer answer) {
        if ( answer == null ) {
            return null;
        }

        AnswerDto.InfoResponse infoResponse = new AnswerDto.InfoResponse();

        if ( answer.getAnswerId() != null ) {
            infoResponse.setAnswerId( answer.getAnswerId() );
        }
        infoResponse.setContents( answer.getContents() );
        infoResponse.setVoteCount( answer.getVoteCount() );
        infoResponse.setAnswerStatus( answer.getAnswerStatus() );
        infoResponse.setCreatedAt( answer.getCreatedAt() );
        infoResponse.setModifiedAt( answer.getModifiedAt() );
        infoResponse.setMember( memberToResponse( answer.getMember() ) );

        return infoResponse;
    }

    protected List<AnswerDto.InfoResponse> answerListToInfoResponseList(List<Answer> list) {
        if ( list == null ) {
            return null;
        }

        List<AnswerDto.InfoResponse> list1 = new ArrayList<AnswerDto.InfoResponse>( list.size() );
        for ( Answer answer : list ) {
            list1.add( answerToInfoResponse( answer ) );
        }

        return list1;
    }

    protected MemberDto.Response memberToResponse1(Member member) {
        if ( member == null ) {
            return null;
        }

        MemberDto.Response response = new MemberDto.Response();

        response.setNumberOfQuestions( countNumberOfQuestions( member.getQuestions() ) );
        response.setNumberOfAnswers( countAnswers( member.getAnswers() ) );
        if ( member.getMemberId() != null ) {
            response.setMemberId( member.getMemberId() );
        }
        response.setEmail( member.getEmail() );
        response.setName( member.getName() );
        response.setPhone( member.getPhone() );
        response.setCreatedAt( member.getCreatedAt() );
        response.setModifiedAt( member.getModifiedAt() );
        response.setMemberStatus( member.getMemberStatus() );

        return response;
    }
}
