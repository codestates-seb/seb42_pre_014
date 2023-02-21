package pre14.stackoverflow.member.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pre14.stackoverflow.member.entity.Member;
import pre14.stackoverflow.exception.BusinessLogicException;
import pre14.stackoverflow.exception.ExceptionCode;
import pre14.stackoverflow.member.repository.MemberRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository=memberRepository;
    }
    public Member createMember(Member member) {
        verifyExistsEmail(member.getEmail());
        return memberRepository.save(member);
    }

    public Member updateMember(Member member) {
        Member findMember=findVerifiedMember(member.getMemberId());

        Optional.ofNullable(member.getName())
                .ifPresent(name -> findMember.setName(name));
        Optional.ofNullable(member.getPhone())
                .ifPresent(phone ->findMember.setPhone(phone));
        Optional.ofNullable(member.getMemberStatus())
                .ifPresent(memberStatus->findMember.setMemberStatus(memberStatus));

        findMember.setModifiedAt(LocalDateTime.now());

        return memberRepository.save(findMember);
    }

    public Member findMember(long memberId) {
        return findVerifiedMember(memberId);
    }

    public Page<Member> findMembers(int page, int size) {
        return memberRepository.findAll(PageRequest.of(page, size, Sort.by("memberId").descending()));
    }
    public void deleteMember(long memberId){
        Member findMember=findVerifiedMember(memberId);

        memberRepository.delete(findMember);
    }
    public Member findVerifiedMember(long memberId) {
        Optional<Member> optionalMember=
                memberRepository.findById(memberId);
        Member findMember=
                optionalMember.orElseThrow(()->
                        new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        return findMember;

    }

    private void verifyExistsEmail(String email){
        Optional<Member> member= memberRepository.findByEmail(email);
        if(member.isPresent())
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
    }
}