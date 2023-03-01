package pre14.stackoverflow.member.service;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pre14.stackoverflow.auth.jwt.JwtTokenizer;
import pre14.stackoverflow.auth.utils.CustomAuthorityUtils;
import pre14.stackoverflow.member.entity.Member;
import pre14.stackoverflow.exception.BusinessLogicException;
import pre14.stackoverflow.exception.ExceptionCode;
import pre14.stackoverflow.member.repository.MemberRepository;
import pre14.stackoverflow.questions.repository.QuestionRepository;
import pre14.stackoverflow.utils.CustomBeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@ToString
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    private final QuestionRepository questionRepository;

    private final CustomBeanUtils customBeanUtils;

    private final CustomAuthorityUtils authorityUtils;

    private final JwtTokenizer jwtTokenizer;

    private final ApplicationEventPublisher publisher;

    private final PasswordEncoder passwordEncoder;

    public Member createMember(Member member) {
        verifyExistsEmail(member.getEmail());

        String password=member.getPassword();
        String encryptedPassword = passwordEncoder.encode(password);
        member.setPassword(encryptedPassword);
        List<String> roles = authorityUtils.createRoles(member.getEmail());
        member.setRoles(roles);

        Member savedMember = memberRepository.save(member);

        // DB에 회원 정보 저장
        return savedMember;
    }
    public Member updateMember(Member member) {
        Member findMember=findVerifiedMember(member.getMemberId());

        Member updatedMember = (Member) customBeanUtils.copyNonNullProperties(member, findMember);

        return memberRepository.save(updatedMember);
    }
    @Transactional(readOnly = true)
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