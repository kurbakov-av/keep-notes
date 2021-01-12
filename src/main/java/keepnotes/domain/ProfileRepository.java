package keepnotes.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource(path = "profile")
public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
