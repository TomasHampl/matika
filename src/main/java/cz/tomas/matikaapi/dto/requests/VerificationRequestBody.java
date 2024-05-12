package cz.tomas.matikaapi.dto.requests;

import cz.tomas.matikaapi.dto.MathOperationTypes;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VerificationRequestBody {

    @NotNull
    private long firstNumber;
    @NotNull
    private long secondNumber;
    @NotNull
    private long result;
    @NotNull
    private MathOperationTypes operationTypes;

}
