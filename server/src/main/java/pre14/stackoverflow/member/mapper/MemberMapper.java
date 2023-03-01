package pre14.stackoverflow.member.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import pre14.stackoverflow.answer.entity.Answer;
import pre14.stackoverflow.member.dto.MemberDto;
import pre14.stackoverflow.member.entity.Member;
import pre14.stackoverflow.questions.entity.Question;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper {
    Member memberPostToMember(MemberDto.Post requestBody);
    Member memberPatchToMember(MemberDto.Patch requestBody);

    @Mapping(source = "questions", target = "numberOfQuestions", qualifiedByName = "countNumberOfQuestions")
    @Mapping(source = "answers", target = "numberOfAnswers", qualifiedByName = "countNumberOfAnswers")
    MemberDto.Response memberToMemberResponse(Member member);
    @Named("countNumberOfQuestions")
    default long countNumberOfQuestions(List<Question> questions) { return questions.size();}
    @Named("countNumberOfAnswers")
    default long countNumberOfAnswers(List<Answer> answers) { return answers.size();}

    List<MemberDto.Response> membersToMemberResponses(List<Member> members);
}
