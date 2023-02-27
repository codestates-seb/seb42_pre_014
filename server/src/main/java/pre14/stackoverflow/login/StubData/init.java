package pre14.stackoverflow.login.StubData;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pre14.stackoverflow.member.entity.Member;
import pre14.stackoverflow.member.repository.MemberRepository;


import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class init {
    private final MemberRepository repository;
    private final PasswordEncoder passwordEncoder;
    @PostConstruct
    public void memberInit() {
        Member member = new Member("test@test.com", "test","010-5555-6666",passwordEncoder.encode("test1234!@#$"));
        member.setMemberId(1L);
        member.setMemberStatus(Member.MemberStatus.MEMBER_ACTIVE);

        repository.save(member);
    }
}
