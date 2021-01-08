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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "note", orphanRemoval = true)
    private List<Attachment> attachments;

    private LocalDateTime remindAt;

    @Embedded
    private String hexColor;

    private boolean archived;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

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
