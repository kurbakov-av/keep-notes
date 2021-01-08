package keepnotes.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "comments")
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String text;

    @ManyToOne(optional = false)
    private Member author;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Attachment> attachments;

    @ManyToOne(optional = false)
    private Note note;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
