package keepnotes.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource(path = "project")
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
