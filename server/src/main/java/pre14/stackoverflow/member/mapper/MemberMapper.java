package pre14.stackoverflow.member.mapper;

import org.mapstruct.Mapper;
import pre14.stackoverflow.member.dto.MemberPatchDto;
import pre14.stackoverflow.member.dto.MemberPostDto;
import pre14.stackoverflow.member.dto.MemberResponseDto;
import pre14.stackoverflow.member.entity.Member;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberPostDtoToMember(MemberPostDto memberPostDto);
    Member memberPatchDtoToMember(MemberPatchDto memberPatchDto);
    MemberResponseDto memberToMemberResponseDto(Member member);

    List<MemberResponseDto> membersToMemberResponseDto(List<Member> members);
}
