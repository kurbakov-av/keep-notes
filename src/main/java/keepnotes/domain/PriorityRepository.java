package keepnotes.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource(path = "priority-note")
public interface PriorityRepository extends JpaRepository<Priority, Long> {
}
