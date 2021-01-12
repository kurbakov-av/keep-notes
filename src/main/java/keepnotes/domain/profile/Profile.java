package keepnotes.domain.profile;

import keepnotes.domain.note.Note;
import keepnotes.domain.user.User;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "profiles")
@Data
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<Note> trashNotes;

    @ManyToOne
    private User owner;
}
