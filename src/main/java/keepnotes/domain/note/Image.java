package keepnotes.domain.note;

import keepnotes.domain.attachment.Attachment;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "images")
@Data
@EqualsAndHashCode(callSuper = true)
public class Image extends Attachment {

    @NotEmpty
    private Integer width;

    @NotEmpty
    private Integer height;
}
