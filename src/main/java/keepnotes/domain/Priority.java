package keepnotes.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "note_priorities")
@Data
public class Priority implements Comparable<Priority> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String title;

    private String description;

    @NotEmpty
    @PositiveOrZero
    private Integer value;

    @Override
    public int compareTo(Priority o) {
        return Integer.compare(value, o.value);
    }
}
