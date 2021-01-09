package keepnotes.domain;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@Embeddable
public class Color {

    @NotEmpty
    @Pattern(regexp = "^[0-9a-fA-F]{6}")
    private String hexValue;

    public void setColor(int red, int green, int blue) {
        hexValue = String.format("%02X%02X%02X", red, green, blue);
    }

    public void setColor(Integer value) {
        this.hexValue = Integer.toHexString(value);
    }
}
