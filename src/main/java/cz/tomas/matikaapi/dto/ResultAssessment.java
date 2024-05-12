package cz.tomas.matikaapi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultAssessment {

    private long providedResult;
    private boolean correct;
    private long correctResult;

}
