package keepnotes.domain;

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
    private Member author;

    @ManyToOne(optional = false)
    private Project project;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "note", orphanRemoval = true)
    private List<Attachment> attachments;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Note> subNotes;

    private LocalDateTime remindAt;

    @Embedded
    private String hexColor;

    private boolean archived;

    @ManyToOne
    private Comment comment;

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

    public void setColor(int red, int green, int blue) {
        hexColor = String.format("#%02X%02X%02X", red, green, blue);
    }

    public void setColor(String hexColor) {
        if (!hexColor.startsWith("#")) {
            hexColor = "#".concat(hexColor);
        }

        this.hexColor = hexColor;
    }
}
