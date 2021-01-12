package keepnotes.domain.note;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource(path = "note")
public interface NoteRepository extends JpaRepository<Note, Long> {
}
