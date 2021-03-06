package keepnotes.domain.note;

import keepnotes.domain.Color;
import keepnotes.domain.Location;
import keepnotes.domain.attachment.Attachment;
import keepnotes.domain.profile.Profile;
import keepnotes.domain.project.Project;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "notes")
@Data
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String title;

    @NotEmpty
    private String text;

    private boolean complete;

    @ManyToOne(optional = false)
    private Profile author;

    @ManyToOne(optional = false)
    private Project project;

    @ManyToOne
    private Image image;

    @Embedded
    private Location location;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "note", orphanRemoval = true)
    private List<Attachment> attachments;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Note> subNotes;

    private LocalDateTime remindAt;

    @Embedded
    private Color color;

    private boolean archived;

    @OrderBy("createdAt DESC")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "note", orphanRemoval = true)
    private List<Comment> comment;

    @ManyToOne(optional = false)
    private Priority priority;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tag> tags;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public void addAttachment(Attachment attachment) {
        attachment.setNote(this);
        attachments.add(attachment);
    }

    public void addSubNote(Note note) {
        this.subNotes.add(note);
    }

    public void setRemindAt(LocalDateTime remindAt) {
        LocalDateTime timeNow = LocalDateTime.now();
        if (timeNow.isAfter(remindAt)) {
            remindAt = timeNow;
        }

        this.remindAt = remindAt;
    }
}
