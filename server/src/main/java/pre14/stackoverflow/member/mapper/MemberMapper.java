package pre14.stackoverflow.member.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pre14.stackoverflow.member.dto.MemberDto;
import pre14.stackoverflow.member.entity.Member;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper {
    Member memberPostToMember(MemberDto.Post requestBody);
    Member memberPatchToMember(MemberDto.Patch requestBody);
    MemberDto.Response memberToMemberResponse(Member member);
    List<MemberDto.Response> membersToMemberResponses(List<Member> members);
}
