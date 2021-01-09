package keepnotes.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource(path = "member")
public interface MemberRepository extends JpaRepository<Profile, Long> {
}
