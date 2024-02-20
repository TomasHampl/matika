package cz.tomas.matikaapi.dto;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Element {

    @Min(1)
    private long maxValue;
    @Min(1)
    private long minValue;
}
