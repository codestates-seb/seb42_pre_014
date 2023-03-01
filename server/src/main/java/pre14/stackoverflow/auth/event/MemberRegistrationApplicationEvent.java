package pre14.stackoverflow.auth.event;

import lombok.Getter;
import pre14.stackoverflow.member.entity.Member;

@Getter
public class MemberRegistrationApplicationEvent {
    private final Member member;
    public MemberRegistrationApplicationEvent(Member member) {
        this.member = member;
    }
}
