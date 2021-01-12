package keepnotes.domain.attachment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource(path = "attachment")
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
}
