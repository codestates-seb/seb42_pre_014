package pre14.stackoverflow.member.service;

import pre14.stackoverflow.member.entity.Member;

import java.util.List;

public class MemberService {
    public Member createMember(Member member) {
        Member createdMember = member;
        return createdMember;
    }

    public Member updateMember(Member member) {
        Member updatedMember = member;
        return updatedMember;
    }

    public Member findMember(long memberId) {
        Member member =
                new Member( "김석진2", "hgd@gmail.com", "010-1234-5678");
        return member;
    }

    public List<Member> findMembers() {
        List<Member> members = List.of(
                new Member( "김석진", "hgd@gmail.com", "010-1324-5678"),
                new Member( "김석진1", "hgd@gmail.com", "010-5555-6666")
        );
        return members;
    }

    public void deleteMember(long memberId) {

    }
}