package pre14.stackoverflow.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pre14.stackoverflow.member.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
}
