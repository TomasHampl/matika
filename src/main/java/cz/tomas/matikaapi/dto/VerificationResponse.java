package cz.tomas.matikaapi.dto;

import cz.tomas.matikaapi.dto.requests.VerificationRequestBody;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VerificationResponse {

    private VerificationRequestBody requestBody;
    private boolean correct;
    private long correctResult;

}
