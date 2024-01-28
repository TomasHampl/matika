package cz.tomas.matikaapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@Jacksonized
@Builder
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public class MathTask {

    private long firstValue;
    private long secondValue;
    private long result;
    @JsonIgnore
    private MathOperationTypes operationType;

}
